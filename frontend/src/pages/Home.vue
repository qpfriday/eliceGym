<script>
import Card from "@/components/Card.vue";
import axios from "axios";
import {computed, onMounted, reactive, ref} from "vue";
import Category from "@/components/Category.vue";
import { useIntersectionObserver } from '@vueuse/core';

export default {
  name: "Home",
  components: {Category, Card},

  setup() {
    const state = reactive({
      items: [],
      searchText: "",
      filteredItems: [],
      selectedCategory: null,
      categoryList: [],
      loading: false,
      isLastPage: false,
      offset: 0,
      limit: 8
    });

    const loadMoreItems = () => {
      if (state.loading || state.isLastPage) return;
      state.loading = true;
      axios.get(`/api/items?offset=${state.items.length}`)
          .then(({ data }) => {
            if (data.length === 0) {
              state.isLastPage = true;
            } else {
              state.items.push(...data);
              state.filteredItems = [...state.items];
              state.offset += data.length;
            }
            state.loading = false;
          })
          .catch(error => {
            console.error("Error loading more items:", error);
            state.loading = false;
          });
    };

    const fetchCategories = () => {
      axios.get("/api/categories").then(response => {
        state.categoryList = response.data;
      }).catch(error => {
        console.error("Failed to fetch categories:", error);
      });
    };

    onMounted(() => {
      fetchCategories();
      state.limit = state.initialLoad;
      loadMoreItems();
      state.limit = 8;
      axios.get("/api/items").then(({data}) => {
        state.items = data;
        state.filteredItems = data;
        state.loading = false;
        console.log("Initial items loaded:", state.items);
        console.log("Initial category selected:", state.selectedCategory);
      });
    });

    axios.get("/api/items").then(({data}) => {
      console.log("API data received:", data);
      state.items = data;
      state.filteredItems = data;
      state.loading = false;
    });

    const intersectionRef = ref(null);
    useIntersectionObserver(
        intersectionRef,
        ([{ isIntersecting }]) => {
          if (isIntersecting) {
            loadMoreItems();
          }
        },
        {
          threshold: 0.5  // Trigger when 50% of the element is visible
        }
    );
    const handleCategorySelected = (categoryId) => {
      state.selectedCategory = categoryId;
    };

    const filteredItemsByCategory = computed(() => {
      console.log("Computing filtered items...");
      console.log("Selected Category:", state.selectedCategory, "Type of selectedCategory:", typeof state.selectedCategory);
      console.log("Total items available for filtering:", state.items.length);

      if (!state.selectedCategory) {
        return state.items;
      } else {
        return state.items.filter(item => {
          const hasCategory = !!item.category;
          console.log("Item name:", item.name, "Has Category:", hasCategory, "Category ID:", item.category?.id);
          return hasCategory && item.category.id === state.selectedCategory;
        });
      }
    });

    const search = () => {
      state.filteredItems = state.searchText ? state.items.filter(item => item.name.includes(state.searchText)) : state.items;
    };

    return { state, search, filteredItemsByCategory, handleCategorySelected, intersectionRef };
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
  <Category :categoryList="state.categoryList" @category-selected="handleCategorySelected" />
  <div class="container">

    <div v-if="!state.loading" class="container">
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
      <div ref="intersectionRef" class="col-12 text-center">
        <div v-if="state.loading" class="spinner-grow text-secondary" style="width: 2rem; height: 2rem;" role="status"></div>
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