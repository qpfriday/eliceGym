<script>
import {onMounted, reactive} from "vue";
import axios from "axios";
//import store from "@/scripts/store";
import router from "@/scripts/router";

export default {
  methods: {
    onInputImage(e) {
      const file = e.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
          this.state.form.imageView = e.target.result;
        };
        reader.readAsDataURL(file);
      }
      this.state.form.img = file;
      this.state.form.imgName = file.name;
      console.log("Selected file:", this.state.form.img);
    },
  },

  setup() {
    const state = reactive({
      form: {
        name: "",
        price: "",
        discountPer: "",
        categoryId: "",
        selection: "",
        description: "",
        stock: "",
        deliveryPrice: "",
        imgPath: "",
        imgView: null,
        imgName: "Choose file",
        img: null,
      },
    });

    const loadCategories = async () => {
      try {
        const response = await axios.get("/api/categories");
        state.categories = response.data;
      } catch (error) {
        console.error("Failed to load categories:", error);
      }
    };

    onMounted(() => {
      loadCategories();
    });

    const add = async () => {
      if (!state.form.categoryId || state.form.categoryId === "") {
        alert("카테고리를 선택해주세요.");
        return; // 카테고리가 선택되지 않았다면 여기서 함수 실행을 중단
      }
      const itemData = {
        name: state.form.name,
        price: state.form.price,
        discount_per: state.form.discountPer,
        category: state.form.categoryId,
        selection: state.form.selection,
        description: state.form.description,
        stock: state.form.stock,
        delivery_price: state.form.deliveryPrice,
      };

      const itemDataBlob = new Blob([JSON.stringify(itemData)], { type: 'application/json' });

      const formData = new FormData();
      formData.append("file", state.form.img); // 파일 데이터 추가
      formData.append("item", itemDataBlob); // JSON 데이터 추가

      console.log("FormData to be sent:", Object.fromEntries(formData));
      console.log("FormData keys and values:");
      for (let key of formData.keys()) {
        console.log(key, formData.getAll(key));
      }
      console.log("item data:", formData.get('item'));

      await axios.post("/api/item/create", formData)
          .then(response => {
            console.log("Response:", response.data);
            router.push({ path: "/" });
          })
          .catch(error => {
            console.error("Error during item creation:", error);
            alert("상품 등록 중 오류가 발생했습니다: " + error.message);
          });
    };
    return { state, add };
  },
};
</script>

<template>
  <div class="container">
    <main class="form-signin">
      <h1 class="h3 mb-3 fw-normal text-center">상품 추가</h1>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">상품명</label>
        <input
          type="text"
          class="form-control"
          id="name"
          placeholder="상품명을 입력해주세요"
          required
          style="margin-bottom: 20px"
          v-model="state.form.name"
        />
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">상품 이미지</label>
        <!-- <input
          type="text"
          class="form-control"
          id="imgPath"
          placeholder="이미지 주소"
          required
          style="margin-bottom: 20px"
          v-model="state.form.imgPath"
        /> -->
      </div>
      <div class="imageView">
        <img
          v-if="state.form.imageView"
          :src="state.form.imageView"
          style="
            width: 100px;
            height: 100px;
            border-radius: 5px;
            margin-bottom: 10px;
          "
        />
      </div>
      <div class="input-group mb-3">
        <div class="custom-file">
          <input
            type="file"
            class="custom-file-input"
            id="customFile"
            aria-describedby="inputGroupFileAddon01"
            ref="fileInput"
            @change="onInputImage($event)"
          />
          <label class="custom-file-label" for="inputGroupFile01">{{
            state.form.imgName
          }}</label>
        </div>
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">가격</label>
        <input
          type="text"
          class="form-control"
          id="price"
          placeholder="숫자만 입력해주세요"
          required
          style="margin-bottom: 20px"
          v-model="state.form.price"
        />
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">할인율 (%)</label>
        <input
          type="text"
          class="form-control"
          id="discount_per"
          placeholder="할인율을 입력해주세요"
          required
          style="margin-bottom: 20px"
          v-model="state.form.discount_per"
        />
      </div>
      <div class="form-floating">
        <label for="category">카테고리</label>
        <select id="category" class="form-select" v-model="state.form.categoryId" required>
          <option disabled value="">카테고리를 선택해주세요</option>
          <option v-for="category in state.categories" :key="category.id" :value="category.id">
            {{ category.name }}
          </option>
        </select>
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">옵션</label>
        <input
          type="text"
          class="form-control"
          id="selection"
          placeholder="옵션을 입력해주세요"
          required
          style="margin-bottom: 20px"
          v-model="state.form.selection"
        />
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">상품 설명</label>
        <textarea
          name="description"
          class="form-control"
          rows="4"
          cols="50"
          style="margin-bottom: 20px"
          v-model="state.form.description"
        ></textarea>
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">재고</label>
        <input
          type="text"
          class="form-control"
          id="stock"
          placeholder="숫자만 입력해주세요"
          required
          style="margin-bottom: 20px"
          v-model="state.form.stock"
        />
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">배송비</label>
        <input
          type="text"
          class="form-control"
          id="delivery_price"
          placeholder="숫자만 입력해주세요"
          required
          style="margin-bottom: 20px"
          v-model="state.form.delivery_price"
        />
      </div>
      <button class="w-100 btn btn-lg btn-success" @click="add">
        등록하기
      </button>
    </main>
  </div>
</template>

<style scoped>
.container {
  margin: 50px auto 100px auto;
  width: 700px;
  padding: 100px 50px 100px 50px;
  border: solid lightgray 0.5px;
  border-radius: 10px;
}
</style>
