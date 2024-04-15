<script>
import Card from "@/components/Card.vue";
import axios from "axios";
import { computed, reactive } from "vue";
import Category from "@/pages/Category.vue";
import { useStore } from "vuex";
import ResultModal from "@/components/ResultModal.vue";
import { moveToCart, moveToLogin } from "@/scripts/lib";

export default {
  name: "Home",

  components: { Category, Card, ResultModal },
  methods: { moveToCart, moveToLogin },
  setup() {
    const store = useStore();
    const state = reactive({
      items: [],
      searchText: "", // 검색어 저장
      filteredItems: [],
      selectedCategory: null,
      loading: true,
      showModal: false,
      accountModal: false,
    });

    axios.get("/api/item/list").then(({ data }) => {
      state.items = data;
      state.filteredItems = data;
      state.loading = false;
    });

    const addToCart = (id) => {
      if (
        store.state.account.id === null ||
        store.state.account.id === undefined
      ) {
        state.accountModal = true;
        return;
      }
      axios.post(`/api/cart/items/${id}`).then(() => {
        state.showModal = true;
        //alert("장바구니에 추가되었습니다.");
        console.log("success");
      });
    };

    const handleCategorySelected = (categoryName) => {
      state.selectedCategory = categoryName;
    };

    const filteredItemsByCategory = computed(() => {
      if (!state.selectedCategory) {
        return state.items;
      } else {
        console.log(typeof state.items[0].parentCategory);
        console.log(typeof state.selectedCategory);
        return state.items.filter(
          (item) => item.parentCategory === state.selectedCategory
        );
      }
    });

    const search = () => {
      state.filteredItems = state.searchText
        ? state.items.filter((item) => item.name.includes(state.searchText))
        : state.items;
    };
    const closeModal = () => {
      state.showModal = false;
      state.accountModal = false;
    };
    return {
      state,
      closeModal,
      search,
      filteredItemsByCategory,
      handleCategorySelected,
      addToCart,
    };
  },
};
</script>

<template>
  <div v-if="state.accountModal">
    <ResultModal
      title="알림"
      content="로그인이 필요합니다."
      btn1="구경하기"
      btn2="로그인하기"
      :moveFunction="moveToLogin"
      :closeFunction="closeModal"
    />
  </div>

  <div v-if="state.showModal">
    <ResultModal
      title="알림"
      content="장바구니에 담겼습니다."
      btn1="계속 쇼핑하기"
      btn2="장바구니 이동"
      :moveFunction="moveToCart"
      :closeFunction="closeModal"
    />
  </div>

  <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
      <li
        data-target="#carouselExampleIndicators"
        data-slide-to="0"
        class="active"
      ></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="../assets/workout.jpg" class="d-block w-100" alt="..." />
      </div>
      <div class="carousel-item">
        <img src="../assets/strap.jpg" class="d-block w-100" alt="..." />
      </div>
      <div class="carousel-item">
        <img src="../assets/protein.jpg" class="d-block w-100" alt="..." />
      </div>
    </div>
    <button
      class="carousel-control-prev"
      type="button"
      data-target="#carouselExampleControls"
      data-slide="prev"
    >
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </button>
    <button
      class="carousel-control-next"
      type="button"
      data-target="#carouselExampleControls"
      data-slide="next"
    >
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </button>
  </div>
  <Category
    :categoryList="categoryList"
    @category-selected="handleCategorySelected"
  />
  <div class="container">
    <div v-if="!state.loading" class="container">
      <div class="form-inline justify-content-between my-3">
        <div class="dropdown my-2 d-flex">
          <button
            class="btn btn-outline-secondary dropdown-toggle"
            type="button"
            data-toggle="dropdown"
            aria-expanded="false"
          >
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
          <input
            type="text"
            class="form-control"
            placeholder="Search..."
            @keyup.enter="search()"
            v-model="state.searchText"
          />
          <div class="input-group-append">
            <button
              class="btn btn-outline-secondary"
              type="button"
              id="button-addon2"
              @click="search"
            >
              검색
            </button>
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
          <div
            class="col my-4"
            v-for="(item, index) in state.filteredItems"
            :key="index"
          >
            <Card :item="item" :addToCart="(id) => addToCart(id)" />
          </div>
        </div>
      </div>

      <!-- 카테고리 필터링 결과를 표시합니다 -->
      <div v-else-if="state.selectedCategory !== null">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-4">
          <div
            class="col my-4"
            v-for="(item, index) in filteredItemsByCategory"
            :key="index"
          >
            <Card :item="item" :addToCart="(id) => addToCart(id)" />
          </div>
        </div>
      </div>

      <!-- 검색과 카테고리 모두 적용되지 않은 경우 모든 상품을 표시합니다 -->
      <div v-else class="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-4">
        <div class="col my-4" v-for="(item, index) in state.items" :key="index">
          <Card :item="item" :addToCart="(id) => addToCart(id)" />
        </div>
      </div>
      <nav aria-label="Pagination" style="margin-top: 20px">
        <ul class="pagination justify-content-center">
          <!-- Previous button -->
          <li class="page-item" :class="{ disabled: state.currentPage === 1 }">
            <button class="page-link" @click="prevPage">Previous</button>
          </li>
          <!-- Render page numbers dynamically -->
          <li
            v-for="pageNumber in visiblePages"
            :key="pageNumber"
            :class="{
              'page-item': true,
              active: state.currentPage === pageNumber,
            }"
          >
            <button class="page-link" @click="changePage(pageNumber)">
              {{ pageNumber }}
            </button>
          </li>
          <!-- Next button -->
          <li
            class="page-item"
            :class="{ disabled: state.currentPage === totalPages }"
          >
            <button class="page-link" @click="nextPage">Next</button>
          </li>
        </ul>
      </nav>
    </div>

    <div
      v-else
      class="d-flex justify-content-center align-items-center"
      style="height: 30vh"
    >
      <div
        class="spinner-grow text-danger"
        style="width: 50px; height: 50px"
        role="status"
      >
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
.carousel-inner {
  max-height: 700px;
}
.carousel-inner img {
  margin-top: -10%;
}
</style>
