<script>
export default {
  name: "Category",
  props: {
    categoryList: Array
  },
  methods: {
    selectAllCategories() {
      this.$emit("category-selected", null);
    },
    selectCategory(categoryName) {
      console.log(categoryName);
      this.$emit("category-selected", categoryName);
    }
  }
};
</script>

<template>
  <div class="categories">
    <ul>
      <li>
        <button type="button" class="btn btn-light" @click="selectAllCategories">전체</button>
      </li>
      <li>
        <button type="button" class="btn btn-light" v-for="category in categoryList" :key="category.id" @click="selectCategory(category.name)">{{ category.name }}</button>
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