<script>
import Card from "@/components/Card.vue";
import axios from "axios";
import { reactive} from "vue";

export default {
  name: "Home",
  components: {Card},

  setup() {

    const state = reactive({
      items: [],
      searchText: "", // 검색어 저장
      filteredItems: [],
      loading: true,
    });

    axios.get("/api/item/list").then(({data}) => {
      state.items = data;
      state.filteredItems = data;
      state.loading = false;
    });

    const search = () => {
      state.filteredItems = state.searchText ? state.items.filter(item => item.name.includes(state.searchText)) : state.items;
    };

    return { state, search };
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
    <div v-if="state.filteredItems.length === 0" class="m-5 text-center">
      <h1>검색한 상품이 없습니다.</h1>
    </div>
    <div v-else class="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-4">
      <div class="col my-4" v-for="(item, index) in state.filteredItems" :key="index">
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
  <div v-else class="d-flex justify-content-center align-items-center" style="height: 30vh;">
    <div class="spinner-grow text-danger" style="width: 50px; height: 50px;" role="status">
      <span class="sr-only">Loading...</span>
    </div>
  </div>
</template>

<style scoped>

a {
  text-decoration: none;
  color: black;
}
</style>