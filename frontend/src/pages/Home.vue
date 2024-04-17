<script>
import Card from "@/components/Card.vue";
import CategoryFilter from "@/components/CategoryFilter.vue";
import SearchBar from "@/components/SearchBar.vue";
import SortOptions from "@/components/SortOptions.vue";
import axios from "axios";
import {computed, onMounted, reactive} from "vue";

export default {
  name: "Home",
  components: {Card, CategoryFilter, SearchBar, SortOptions},
  setup() {
    const state = reactive({
      items: [],
      searchText: "",
      filteredItems: [],
      selectedCategory: null,
      categoryList: [],
      loading: false,
      sortOptions: [
        { value: 'discountHigh', text: '높은 할인률순' },
        { value: 'discountLow', text: '낮은 할인률순' },
        { value: 'priceHigh', text: '높은 가격순' },
        { value: 'priceLow', text: '낮은 가격순' }
      ]
    });

    const sortItems = (criteria) => {
      switch (criteria) {
        case 'discountHigh':
          state.items.sort((a, b) => b.discountPer - a.discountPer);
          break;
        case 'discountLow':
          state.items.sort((a, b) => a.discountPer - b.discountPer);
          break;
        case 'priceHigh':
          state.items.sort((a, b) => b.price - a.price);
          break;
        case 'priceLow':
          state.items.sort((a, b) => a.price - b.price);
          break;
      }
      state.filteredItems = [...state.items];
    };

    const handleCategorySelected = categoryId => {
      console.log('Category selected with ID:', categoryId);
      state.selectedCategory = categoryId;
      state.filteredItems = state.items.filter(item => item.category && item.category.id === state.selectedCategory);
    };

    const handleSearch = searchTerm => {
      state.searchText = searchTerm;
      state.filteredItems = [];
      state.filteredItems = state.items.filter(item => item.name.toLowerCase().includes(searchTerm.toLowerCase()));
      console.log("Filtered items after search:", state.filteredItems);
    };

    const filteredItemsByCategory = computed(() => {
      if (state.searchText) {
        return state.items.filter(item => item.name.toLowerCase().includes(state.searchText.toLowerCase()));
      } else if (state.selectedCategory) {
        return state.items.filter(item => item.categoryId === state.selectedCategory);
      }
      return state.items;
    });

    onMounted(async () => {
      state.loading = true;
      try {
        const { data } = await axios.get("/api/items");
        state.items = data;
        state.filteredItems = data;
      } catch (error) {
        console.error("Error loading items:", error);
      }
      state.loading = false;
    });

    return { state, handleCategorySelected, handleSearch, filteredItemsByCategory, sortItems};
  }
};
</script>

<template>
  <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
      <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="../assets/workout.jpg" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="../assets/strap.jpg" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="../assets/protein.jpg" class="d-block w-100" alt="...">
      </div>
    </div>
    <button class="carousel-control-prev" type="button" data-target="#carouselExampleControls" data-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-target="#carouselExampleControls" data-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </button>
  </div>
  <CategoryFilter :categoryList="state.categoryList" @category-selected="handleCategorySelected" />
  <div class="d-flex justify-content-between align-items-center filter-search-bar">
    <SortOptions :options="state.sortOptions" @option-selected="sortItems" />
    <SearchBar @search="handleSearch" />
  </div>
  <div class="container">
    <div v-if="!state.loading">
      <div v-if="state.searchText !== '' && state.filteredItems.length === 0" class="m-5 text-center">
        <h1>검색한 상품이 없습니다.</h1>
      </div>
      <div v-else class="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-4">
        <Card v-for="(item, index) in filteredItemsByCategory" :key="index" :item="item"/>
      </div>
    </div>
    <div v-else class="d-flex justify-content-center align-items-center" style="height: 30vh;">
      <div class="spinner-grow text-danger" style="width: 50px; height: 50px;" role="status">
        <span class="sr-only">Loading...</span>
      </div>
    </div>
  </div>
</template>

<style scoped>
.filter-search-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
a {
  text-decoration: none;
  color: black;
}
.carousel-inner{
  max-height: 700px;
}
.carousel-inner img {
  margin-top: -10%;
}
</style>