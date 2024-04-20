<script>
import { ref, onMounted } from "vue";
//import axios from "axios";
import { useRoute } from "vue-router";
import { router, ROUTER_LINKS } from "@/scripts/router";
import baseURL from "@/scripts/baseURL";

export default {
  setup() {
    const route = useRoute();
    const categoryId = ref(route.params.id);
    const state = ref({
      name: "",
      description: "",
    });

    onMounted(() => {
      console.log("Category ID on mount:", categoryId.value);
      if (!categoryId.value) {
        console.error("Category ID is undefined!");
        router.push(ROUTER_LINKS.CATEGORY_MANAGEMENT.path);
      } else {
        loadCategory();
      }
    });

    // 카테고리 데이터 로딩
    const loadCategory = () => {
      if (!categoryId.value) {
        console.error("Category ID is undefined!");
        router.push("/"); // ID가 없을 경우 목록 페이지로 리다이렉션
        return;
      }

      baseURL
        .get(`/api/categories/${categoryId.value}`)
        .then((response) => {
          state.value = {
            name: response.data.name,
            description: response.data.description,
          };
        })
        .catch((error) => {
          console.error("Failed to load category:", error);
        });
    };

    // 카테고리 업데이트
    const updateCategory = () => {
      baseURL
        .put(`/api/categories/${categoryId.value}`, state.value)
        .then(() => {
          console.log("Category updated successfully");

          router.push("/categoryManagement"); // 수정 후 목록 페이지로 이동
        })
        .catch((error) => {
          console.error("Failed to update category:", error);
          alert("Failed to update category.");
        });
    };

    onMounted(loadCategory);

    return { state, updateCategory };
  },
};
</script>

<template>
  <div class="container">
    <h1 class="h3 mb-3 fw-normal text-center">카테고리 수정</h1>
    <input
      type="text"
      class="form-control mb-3"
      v-model="state.name"
      placeholder="카테고리 이름"
    />
    <textarea
      class="form-control mb-3"
      v-model="state.description"
      placeholder="카테고리 설명"
    ></textarea>
    <button @click="updateCategory" class="btn btn-success">저장하기</button>
  </div>
</template>

<style scoped>
.container {
  margin: 50px auto;
  width: 700px;
  padding: 50px;
  border: 0.5px solid lightgray;
  border-radius: 10px;
}
</style>
