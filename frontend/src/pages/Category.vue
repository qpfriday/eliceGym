<script>
import axios from "axios";

export default {
  name: "Category",
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
          })
          .catch(error => {
            console.error("Failed to fetch categories:", error);
          });
    },
    selectAllCategories() {
      this.$emit("category-selected", null);
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
  <div class="container d-flex justify-content-center align-items-center my-5">
    <div class=" btn-group-toggle" data-toggle="buttons" style="margin-bottom: 50px">
      <label class="btn btn-outline-secondary btn-lg active m-4" @click="selectAllCategories" style="width: 200px; border-radius: 0">
        <input type="radio" name="options" id="option1" checked> 전체
      </label>
      <label class="btn btn-outline-secondary btn-lg m-4" v-for="category in categoryList" :key="category.id" style="width: 200px; border-radius: 0"
             @click="selectCategory(category.name)">
        <input type="radio" name="options" id="option2"> {{ category.name }}
      </label>
    </div>
  </div>

</template>

<style scoped>
</style>