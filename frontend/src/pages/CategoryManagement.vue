<script>
import axios from "axios";
import category from "@/components/Category.vue";

export default {
  computed: {
    category() {
      return category;
    }
  },
  data() {
    return {
      categoryList: [],
      loading: true
    };
  },
  methods: {
    fetCategories() {
      this.loading = true;
      axios.get("/api/categories")
          .then(response => {
            this.categoryList = response.data;
            console.log("Fetched categories:", this.categoryList);

          })
          .catch(error => {
            console.error("Failed to fetch categories:", error);
          })
          .finally(() => {
            this.loading = false; // Set loading state to false after request completes
          });
    },
    deleteCategory(categoryId, categoryName) {
      if (confirm(`'${categoryName}' 카테고리를 삭제하시겠습니까?`)) {
        axios.delete(`/api/categories/${categoryId}`)
            .then(() => {
              this.fetCategories(); // 삭제 후 목록을 다시 불러옵니다.
              console.log("Category successfully deleted");
            })
            .catch(error => {
              console.error("Error deleting category:", error);
              alert("Failed to delete category");
            });
      }
    },
    selectCategory(categoryName) {
      console.log(categoryName);
      this.$emit("category-selected", categoryName);
    }
  },
  mounted() {
    this.fetCategories();
  }
};
</script>

<template>
  <div class="py-5 text-center">
    <h2>카테고리 목록</h2>
  </div>
  <div v-if="!loading" class="container" style="width:700px">
    <ul class="list-group">
      <li v-for="category in categoryList" :key="category.id" class="list-group-item d-flex justify-content-between align-items-center py-4">
        <div>
          <h3 class="mb-0">{{ category.name }}</h3>
          <p class="mb-0 opacity-75">{{ category.description }}</p>
        </div>
        <div>
          <button type="button" class="btn btn-danger btn-lg mx-3" @click.stop="deleteCategory(category.id, category.name)">삭제</button>
          <router-link :to="`/modify-category/${category.id}`" class="btn btn-warning btn-lg">수정</router-link>
        </div>
      </li>
    </ul>
    <router-link to="/add-category" class="btn btn-primary btn-block mt-4 addCategory">추가</router-link>
  </div>
  <div v-else class="d-flex justify-content-center align-items-center" style="height: 30vh;">
    <div class="spinner-grow text-danger" style="width: 50px; height: 50px;" role="status">
      <span class="sr-only">Loading...</span>
    </div>
  </div>
</template>


<style scoped>

.addCategory {
  width: 200px;
  padding: 10px 10px;
  font-size: 20px;
  display: block;
  margin: 0 auto;
}

</style>
