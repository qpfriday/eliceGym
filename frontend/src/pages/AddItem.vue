<script>
import { reactive } from "vue";
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
    },
  },

  setup() {
    const state = reactive({
      form: {
        name: "",
        price: "",
        discount_per: "",
        parent_category: "",
        child_category: "",
        selection: "",
        description: "",
        stock: "",
        delivery_price: "",
        imgPath: "",
        imgView: null,
        imgName: "Choose file",
        img: null,
      },
    });

    const add = async () => {
      console.log(state.form.img);
      const formData = new FormData();

      formData.append("file", state.form.img);
      formData.append("imgName", state.form.imgName);
      formData.append("name", state.form.name);
      formData.append("price", state.form.price);
      formData.append("discount_per", state.form.discount_per);
      formData.append("parent_category", state.form.parent_category);
      formData.append("child_category", state.form.child_category);
      formData.append("selection", state.form.selection);
      formData.append("description", state.form.description);
      formData.append("stock", state.form.stock);
      formData.append("delivery_price", state.form.delivery_price);

      await axios
        .post("/api/item/create", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((res) => {
          console.log(res);
          router.push({ path: "/" });
        })
        .catch((err) => {
          console.log(err);
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
        <label for="floatingInput" class="form-label">상위 카테고리</label>
        <input
          type="text"
          class="form-control"
          id="parent_category"
          placeholder="상위 카테고리를 입력해주세요"
          required
          style="margin-bottom: 20px"
          v-model="state.form.parent_category"
        />
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">하위 카테고리</label>
        <input
          type="text"
          class="form-control"
          id="chile_category"
          placeholder="하위 카테고리를 입력해주세요"
          required
          style="margin-bottom: 20px"
          v-model="state.form.child_category"
        />
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
        <label for="floatingInput" class="form-label">숫자만 입력해주세요</label>
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
