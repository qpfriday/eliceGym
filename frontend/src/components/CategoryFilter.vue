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
      class="container d-flex align-items-center my-4"
    >
      <div
        class="btn-group-toggle"
        data-toggle="buttons"
      >
        <label
          class="btn btn-outline-success btn active m-2"
          @click="selectAllCategories"
          style="width: 130px; border-radius: 0"
        >
          <input type="radio" name="options" id="option1" checked /> 전체
        </label>
        <label
          class="btn btn-outline-success btn m-2"
          v-for="category in categoryList"
          :key="category.id"
          style="width: 130px; border-radius: 0"
          @click="selectCategory(category.id)"
        >
          <input type="radio" name="options" id="option2" /> {{ category.name }}
        </label>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
