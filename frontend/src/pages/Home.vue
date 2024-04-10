<script>
import Card from "@/components/Card.vue";
import axios from "axios";
import {computed, reactive} from "vue";
import Category from "@/pages/Category.vue";

export default {
  name: "Home",
  components: {Category, Card},

  setup() {

    const categoryList = [
      { id: 1, name: "의류" },
      { id: 2, name: "보충제" },
      { id: 3, name: "용품" }
    ];

    const state = reactive({
      items: [],
      searchText: "", // 검색어 저장
      filteredItems: [],
      selectedCategory: null
    });

    axios.get("/api/item/list").then(({data}) => {
      state.items = data;
      state.filteredItems = data;
    });

    const handleCategorySelected = (categoryName) => {
      state.selectedCategory = categoryName;
    };

    const filteredItemsByCategory = computed(() => {
      if (!state.selectedCategory) {
        return state.items;
      } else {
        console.log(typeof state.items[0].parentCategory);
        console.log(typeof state.selectedCategory);
        return state.items.filter(item => item.parentCategory === state.selectedCategory);
      }
    });

    const search = () => {
      state.filteredItems = state.searchText ? state.items.filter(item => item.name.includes(state.searchText)) : state.items;
    };

    return { state, search, categoryList, filteredItemsByCategory, handleCategorySelected };
  }
};
</script>

<template>
  <div class="align-middle bg-danger text-white d-flex justify-content-center align-items-center" style="height: 300px">
    <div class="text-center">
      <h1 class="display-4 fw-bolder">엘리스짐</h1>
      <h5 class="lead fw-normal text-white-50">불가능 한 것을 이루는 유일한 방법은 가능하다고 믿는 것 입니다!</h5>
    </div>
  </div>
  <Category :categoryList="categoryList" @category-selected="handleCategorySelected" />
  <div class="container">
    <div class="form-inline justify-content-between my-3">
      <div class="dropdown my-2 d-flex">
        <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">
          정렬 기준
        </button>
        <ul class="dropdown-menu">
          <a class="dropdown-item">높은 할인률순</a>
          <a class="dropdown-item">낮은 할인률순</a>
          <a class="dropdown-item">높은 가격순</a>
          <a class="dropdown-item">낮은 가격순</a>
        </ul>
      </div>
      <div class="input-group">
        <input type="text" class="form-control" placeholder="Search..." @keyup.enter="search()" v-model="state.searchText">
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button" id="button-addon2" @click="search">검색</button>
        </div>
      </div>
    </div>
    <div v-if="state.searchText !== ''">
      <!-- 검색 결과가 없는 경우 -->
      <div v-if="state.filteredItems.length === 0" class="m-5 text-center">
        <h1>검색한 상품이 없습니다.</h1>
      </div>
      <!-- 검색 결과가 있는 경우 -->
      <div v-else class="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-4">
        <div class="col my-4" v-for="(item, index) in state.filteredItems" :key="index">
          <Card :item="item"/>
        </div>
      </div>
    </div>

    <!-- 카테고리 필터링 결과를 표시합니다 -->
    <div v-else-if="state.selectedCategory !== null">
      <div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-4">
        <div class="col my-4" v-for="(item, index) in filteredItemsByCategory" :key="index">
          <Card :item="item"/>
        </div>
      </div>
    </div>

    <!-- 검색과 카테고리 모두 적용되지 않은 경우 모든 상품을 표시합니다 -->
    <div v-else class="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-4">
      <div class="col my-4" v-for="(item, index) in state.items" :key="index">
        <Card :item="item"/>
      </div>
    </div>
    <nav aria-label="Pagination" style="margin-top: 20px;">
      <ul class="pagination justify-content-center">
        <!-- Previous button -->
        <li class="page-item" :class="{ 'disabled': state.currentPage === 1 }">
          <button class="page-link" @click="prevPage">Previous</button>
        </li>
        <!-- Render page numbers dynamically -->
        <li v-for="pageNumber in visiblePages" :key="pageNumber" :class="{ 'page-item': true, 'active': state.currentPage === pageNumber }">
          <button class="page-link" @click="changePage(pageNumber)">{{ pageNumber }}</button>
        </li>
        <!-- Next button -->
        <li class="page-item" :class="{ 'disabled': state.currentPage === totalPages }">
          <button class="page-link" @click="nextPage">Next</button>
        </li>
      </ul>
    </nav>

  </div>
</template>

<style scoped>

a {
  text-decoration: none;
  color: black;
}
</style>