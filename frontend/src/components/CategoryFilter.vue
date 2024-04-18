<script>
import axios from "axios";

export default {
  name: "CategoryFilter",
  data(){
    return{
      categoryList:[]
    };
  },
  methods: {
    fetCategories(){
      axios.get("/api/categories")
          .then(response => {
            this.categoryList = response.data;
          })
          .catch(error => {
            console.error("Failed to fetch categories:", error);
          });
    },
    selectCategory(categoryId) {
      console.log('Emitting category-selected event with ID:', categoryId);
      this.$emit('category-selected', categoryId);
    }
  },
  mounted() {
    this.fetCategories();
  }
}
</script>

<template>
  <div class="categories">
    <ul>
      <li>
        <button type="button" class="btn btn-light" v-for="category in categoryList" :key="category.id" @click="selectCategory(category.id)">{{ category.name }}</button>
      </li>
    </ul>
  </div>
</template>

<style scoped>
.categories{
  padding-right: 11%;
  padding-left:  11%;
}
.categories ul {
  list-style-type: none;
  padding: 0;
  text-align: center;
}
.categories li {
  display: inline-block;
  margin-top: 20px;
}
</style>
