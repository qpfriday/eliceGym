<script>
import baseURL from "@/scripts/baseURL";
//import axios from "axios";

export default {
  name: "CategoryFilter",
  data() {
    return {
      categoryList: [],
    };
  },
  methods: {
    fetCategories() {
      baseURL
        .get("/api/categories")
        .then((response) => {
          this.categoryList = response.data;
        })
        .catch((error) => {
          console.error("Failed to fetch categories:", error);
        });
    },
    selectCategory(categoryId) {
      console.log("Emitting category-selected event with ID:", categoryId);
      this.$emit("category-selected", categoryId);
    },

    selectAllCategories() {
      this.$emit("category-selected", null);
    },
  },
  mounted() {
    this.fetCategories();
  },
};
</script>

<template>
  <div class="categories">
    <div
      class="container d-flex justify-content-center align-items-center my-5"
    >
      <div
        class="btn-group-toggle"
        data-toggle="buttons"
        style="margin-bottom: 50px"
      >
        <label
          class="btn btn-outline-secondary btn-lg active m-4"
          @click="selectAllCategories"
          style="width: 200px; border-radius: 0"
        >
          <input type="radio" name="options" id="option1" checked /> 전체
        </label>
        <label
          class="btn btn-outline-secondary btn-lg m-4"
          v-for="category in categoryList"
          :key="category.id"
          style="width: 200px; border-radius: 0"
          @click="selectCategory(category.id)"
        >
          <input type="radio" name="options" id="option2" /> {{ category.name }}
        </label>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
