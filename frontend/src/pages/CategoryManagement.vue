<script>
import axios from "axios";
import CategoryFilter from "@/components/CategoryFilter.vue";

export default {
  computed: {
    category() {
      return CategoryFilter;
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
            this.loading = false;
          });
    },
    deleteCategory(categoryId, categoryName) {
      if (confirm(`'${categoryName}' 카테고리를 삭제하시겠습니까?`)) {
        axios.delete(`/api/categories/${categoryId}`)
            .then(() => {
              this.fetCategories();
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
        <div class="category-content">
          <h3 class="mb-0">{{ category.name }}</h3>
          <p class="mb-0 opacity-75">{{ category.description }}</p>
        </div>
        <div>
          <router-link :to="`/modify-category/${category.id}`" class="btn custom-edit-button btn-lg">
            <i class="bi bi-pencil-fill"></i>
          </router-link>
          <button type="button" class="btn custom-delete-button btn-lg mx-3" @click.stop="deleteCategory(category.id, category.name)">
            <i class="bi bi-trash"></i>
          </button>
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
@import 'bootstrap-icons/font/bootstrap-icons.css';
.category-content {
  padding-left: 20px; /* 왼쪽에서 20px 떨어진 위치에서 시작하도록 패딩 추가 */
}
.list-group-item {
  background: #ffffff;
  box-shadow: 0 2px 5px rgba(0,0,0,0.15);
  border: none;
  margin-bottom: 10px;
  border-radius: 10px;
}
.list-group {
  padding: 20px;
  border-radius: 15px;
}
.btn i {
  padding: 5px;
  color: #6c757d;
  transition: color 0.3s;
}
.btn:hover i {
  color: black;
}
.custom-delete-button, .custom-edit-button {
  background-color: transparent;
  border: 1px solid transparent;
  border-radius: 5px;
}
.custom-edit-button:hover {
  border-color: transparent;
}
.addCategory {
  width: 200px;
  padding: 10px 10px;
  font-size: 20px;
  display: block;
  margin: 0 auto;
}
</style>

