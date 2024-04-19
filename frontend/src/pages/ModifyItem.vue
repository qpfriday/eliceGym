<script>
import { onMounted, reactive } from "vue";
//import axios from "axios";
import { useRoute } from "vue-router";
import { ROUTER_LINKS } from "@/scripts/router";
import baseURL from "@/scripts/baseURL";

export default {
  name: "modifyItem",
  computed: {
    ROUTER_LINKS() {
      return ROUTER_LINKS;
    },
  },
  setup() {
    const route = useRoute();
    const itemId = route.params.itemId;
    const state = reactive({
      loading: true,
      categories: [],
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
      editable: false,
    });

    const loadItemAndCategories = async () => {
      try {
        // 카테고리 데이터를 먼저 로드합니다.
        const categoryResponse = await baseURL.get("/api/categories");
        console.log("Categories Loaded:", categoryResponse.data);
        state.categories = categoryResponse.data;

        // 상품 데이터를 로드합니다.
        const itemResponse = await baseURL.get(`/api/item/${itemId}`);
        console.log("Item Loaded:", itemResponse.data);
        state.form = { ...state.form, ...itemResponse.data };
        console.log("Form State after Item Load:", state.form);
        state.img = `data:image/jpeg;base64,${itemResponse.data.img}`;

        // 카테고리 ID를 설정합니다.
        if (state.categories.length > 0) {
          state.form.categoryId = itemResponse.data.categoryId;
          console.log("Category ID Set:", state.form.categoryId);
        }

        state.loading = false;
      } catch (error) {
        console.error("Failed to load data:", error);
        state.loading = false;
      }
    };

    onMounted(loadItemAndCategories);

    const edit = () => {
      state.editable = true;
    };

    const save = () => {
      console.log("Saving data:", state.form);
      baseURL
        .put("/api/item/update", state.form)
        .then(() => {
          alert("수정이 완료되었습니다");
          state.editable = false;
        })
        .catch((error) => {
          console.error("Update failed:", error);
          alert("Update failed!");
        });
    };

    return { state, edit, save };
  },
};
</script>

<template>
  <div
    class="container justify-content-center align-items-center"
    v-if="!state.loading"
  >
    <main class="form-signin">
      <h1 class="h3 mb-3 fw-normal text-center">상품 정보</h1>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">상품명</label>
        <input
          type="text"
          class="form-control"
          id="name"
          placeholder="상품명을 입력해주세요"
          aria-label="Disabled input example"
          disabled
          style="margin-bottom: 20px"
          v-if="!state.editable"
          v-model="state.form.name"
        />
        <input
          type="text"
          class="form-control"
          id="name"
          placeholder="상품명을 입력해주세요"
          required
          style="margin-bottom: 20px"
          v-else
          v-model="state.form.name"
        />
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">가격</label>
        <input
          type="text"
          class="form-control"
          id="price"
          placeholder="Disabled"
          aria-label="Disabled input example"
          disabled
          style="margin-bottom: 20px"
          v-if="!state.editable"
          v-model="state.form.price"
        />
        <input
          type="text"
          class="form-control"
          id="price"
          placeholder="숫자만 입력해주세요"
          required
          style="margin-bottom: 20px"
          v-else
          v-model="state.form.price"
        />
      </div>
      <div class="form-floating">
        <label for="floatingPassword" class="form-label">할인율 (%)</label>
        <input
          type="text"
          class="form-control"
          id="discount_per"
          placeholder="할인율을 입력해주세요"
          aria-label="Disabled input example"
          disabled
          style="margin-bottom: 20px"
          v-if="!state.editable"
          v-model="state.form.discountPer"
        />
        <input
          type="text"
          class="form-control"
          id="discount_per"
          placeholder="할인율을 입력해주세요"
          required
          style="margin-bottom: 20px"
          v-else
          v-model="state.form.discountPer"
        />
      </div>
      <div class="form-floating">
        <label for="category">카테고리</label>
        <select
          id="category"
          class="form-select"
          v-model="state.form.categoryId"
          :disabled="!state.editable"
          required
        >
          <option disabled value="">
            {{ state.loading ? "로딩 중..." : "카테고리를 선택해주세요" }}
          </option>
          <option
            v-for="category in state.categories"
            :key="category.id"
            :value="category.id"
          >
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
          aria-label="Disabled input example"
          disabled
          style="margin-bottom: 20px"
          v-if="!state.editable"
          v-model="state.form.selection"
        />
        <input
          type="text"
          class="form-control"
          id="selection"
          placeholder="옵션을 입력해주세요"
          required
          style="margin-bottom: 20px"
          v-else
          v-model="state.form.selection"
        />
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">상품 설명</label>
        <input
          type="text"
          class="form-control"
          id="floatingInput"
          placeholder="description"
          aria-label="Disabled input example"
          disabled
          style="margin-bottom: 20px"
          v-if="!state.editable"
          v-model="state.form.description"
        />
        <input
          type="email"
          class="form-control"
          id="floatingInput"
          placeholder="description"
          required
          style="margin-bottom: 20px"
          v-else
          v-model="state.form.description"
        />
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">수량 (개)</label>
        <input
          type="text"
          class="form-control"
          id="stock"
          placeholder="숫자만 입력해주세요"
          aria-label="Disabled input example"
          disabled
          style="margin-bottom: 20px"
          v-if="!state.editable"
          v-model="state.form.stock"
        />
        <input
          type="email"
          class="form-control"
          id="stock"
          placeholder="숫자만 입력해주세요"
          required
          style="margin-bottom: 20px"
          v-else
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
          aria-label="Disabled input example"
          disabled
          style="margin-bottom: 20px"
          v-if="!state.editable"
          v-model="state.form.deliveryPrice"
        />
        <input
          type="email"
          class="form-control"
          id="delivery_price"
          placeholder="숫자만 입력해주세요"
          required
          style="margin-bottom: 20px"
          v-else
          v-model="state.form.deliveryPrice"
        />
      </div>
      <button
        class="w-100 btn btn-lg btn-success"
        @click="edit"
        v-if="!state.editable"
      >
        수정
      </button>
      <button
        class="w-100 btn btn-lg btn-success"
        @click="save"
        v-if="state.editable"
      >
        저장
      </button>
      <router-link :to="ROUTER_LINKS.PRODUCT_DETAIL.path"
        >상품 페이지로</router-link
      >
    </main>
  </div>
  <div
    v-else
    class="d-flex justify-content-center align-items-center"
    style="height: 30vh; margin-top: 200px"
  >
    <div
      class="spinner-grow text-success"
      style="width: 50px; height: 50px"
      role="status"
    >
      <span class="sr-only">Loading...</span>
    </div>
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
