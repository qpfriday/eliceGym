<script>
import axios from "axios";
import category from "./Category.vue";

export default {
  computed: {
    category() {
      return category;
    }
  },
  data() {
    return {
      categoryList: []
    };
  },
  methods: {
    fetCategories() {
      axios.get("/api/categories")
          .then(response => {
            this.categoryList = response.data;
            console.log("Fetched categories:", this.categoryList);
          })
          .catch(error => {
            console.error("Failed to fetch categories:", error);
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
  <div class="d-flex flex-column flex-md-row p-4 gap-4 py-md-5 align-items-center justify-content-center">
    <div class="list-group">
      <a href="#" class="list-group-item list-group-item-action d-flex gap-3 py-3" v-for="category in categoryList" :key="category.id" @click="selectCategory(category.name)">
        <div class="d-flex gap-2 w-100 justify-content-between">
          <div>
            <h6 class="mb-0">{{ category.name }}</h6>
            <p class="mb-0 opacity-75">{{ category.description }}</p>
          </div>
          <router-link :to="`/modify-category/${category.id}`" class="btn btn-primary modifyCategory">수정</router-link>
          <button type="button" class="btn btn-danger" @click.stop="deleteCategory(category.id, category.name)">삭제</button>
        </div>
      </a>
    </div>
  </div>
  <router-link to="/add-category" class="btn btn-primary addCategory">추가</router-link>
</template>

<style scoped>
.modifyCategory {
  width: 50px;
  padding: 10px 10px;
  font-size: 13px;
  display: block;
  margin: 0 auto;
}
.addCategory {
  width: 200px;
  padding: 10px 10px;
  font-size: 20px;
  display: block;
  margin: 0 auto;
}
</style>
