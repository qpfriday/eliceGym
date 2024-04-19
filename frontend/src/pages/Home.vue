<script>
import { useIntersectionObserver } from "@vueuse/core";
import Card from "@/components/Card.vue";
import CategoryFilter from "@/components/CategoryFilter.vue";
import SearchBar from "@/components/SearchBar.vue";
import SortOptions from "@/components/SortOptions.vue";
//import axios from "axios";
import { computed, onMounted, onUnmounted, reactive, ref } from "vue";
import store from "@/scripts/store";
import ResultModal from "@/components/ResultModal.vue";
import { moveToCart, moveToLogin } from "@/scripts/lib";
import baseURL from "@/scripts/baseURL";

export default {
  name: "Home",
  components: { Card, CategoryFilter, SearchBar, SortOptions, ResultModal },
  methods: { moveToCart, moveToLogin },
  setup() {
    const state = reactive({
      items: [],
      searchText: "",
      filteredItems: [],
      selectedCategory: null,
      categoryList: [],
      loading: false,
      showModal: false,
      accountModal: false,
      pageNumber: 0,
      isFirstLoad: true,
      allItemsLoaded: false,
      sortOptions: [
        { value: "discountHigh", text: "높은 할인률순" },
        { value: "discountLow", text: "낮은 할인률순" },
        { value: "priceHigh", text: "높은 가격순" },
        { value: "priceLow", text: "낮은 가격순" },
      ],
    });

    const loadMoreRef = ref(null);
    let stop = null;

    const loadItems = async () => {
      if (state.loading || state.allItemsLoaded) return;
      state.loading = true;

      const itemsPerPage = state.isFirstLoad ? 16 : 8; // 첫 번째 로드시 16개 아이템, 그 후 8개
      try {
        const response = await baseURL.get(
          `/api/items?page=${state.pageNumber}&size=${itemsPerPage}`
        );
        const data = response.data.content; // API 응답에서 상품 데이터 추출
        if (data.length > 0) {
          if (state.isFirstLoad) {
            state.items = data;
            state.isFirstLoad = false;
          } else {
            // 중복 로드 방지 로직
            const newItems = data.filter(
              (item) =>
                !state.items.some((existItem) => existItem.id === item.id)
            );
            state.items.push(...newItems);
          }
          state.pageNumber++;
        } else {
          state.allItemsLoaded = true;
        }
      } catch (error) {
        console.error("Error loading items:", error);
      } finally {
        state.loading = false;
      }
    };

    // const { stop } = useIntersectionObserver(
    //   target,
    //   ([{ isIntersecting }]) => {
    //     if (isIntersecting) {
    //       loadItems();
    //     }
    //   },
    //   { threshold: 0.5 }
    // );

    // onUnmounted(() => {
    //   stop();
    // });

    const sortItems = (criteria) => {
      switch (criteria) {
        case "discountHigh":
          state.items.sort((a, b) => b.discountPer - a.discountPer);
          break;
        case "discountLow":
          state.items.sort((a, b) => a.discountPer - b.discountPer);
          break;
        case "priceHigh":
          state.items.sort((a, b) => b.price - a.price);
          break;
        case "priceLow":
          state.items.sort((a, b) => a.price - b.price);
          break;
      }
      state.filteredItems = [...state.items];
    };

    const addToCart = (id) => {
      if (
        store.state.account.id === null ||
        store.state.account.id === undefined
      ) {
        state.accountModal = true;
        return;
      }
      baseURL
        .post(`/api/cart/items/${id}`)
        .then(() => {
          state.showModal = true;
          console.log("Item added to cart successfully.");
        })
        .catch((error) => {
          console.error("Failed to add item to cart:", error);
        });
    };

    const closeModal = () => {
      state.showModal = false;
      state.accountModal = false;
    };

    const handleCategorySelected = (categoryId) => {
      state.selectedCategory = categoryId;
      handleSearch(state.searchText);
    };

    const handleSearch = (searchTerm) => {
      state.searchText = searchTerm.toLowerCase();
      state.filteredItems = state.items.filter(
        (item) =>
          item.name.toLowerCase().includes(state.searchText) &&
          (!state.selectedCategory ||
            item.categoryId === state.selectedCategory)
      );
    };

    const filteredItemsByCategory = computed(() => {
      return state.items.filter(
        (item) =>
          (!state.searchText ||
            item.name.toLowerCase().includes(state.searchText.toLowerCase())) &&
          (!state.selectedCategory ||
            item.categoryId === state.selectedCategory)
      );
    });

    onMounted(() => {
      const { stop: localStop } = useIntersectionObserver(
        loadMoreRef,
        ([{ isIntersecting }]) => {
          if (isIntersecting && !state.allItemsLoaded) {
            loadItems();
          }
        },
        { threshold: 0.1 } // 임계값 조정
      );
      stop = localStop;
      loadItems(); // 초기 아이템 로드
    });
    onUnmounted(() => {
      if (stop) stop();
    });

    return {
      state,
      loadMoreRef,
      loadItems,
      addToCart,
      closeModal,
      handleCategorySelected,
      handleSearch,
      filteredItemsByCategory,
      sortItems,
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
        <img src="../assets/outdoor.jpg" class="d-block w-100" alt="..." />
      </div>
      <div class="carousel-item">
        <img src="../assets/shoes.jpg" class="d-block w-100" alt="..." />
      </div>
      <div class="carousel-item">
        <img src="../assets/greenprotein.jpg" class="d-block w-100" alt="..." />
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
  <div class="container">
    <CategoryFilter
      :categoryList="state.categoryList"
      @category-selected="handleCategorySelected"
    />
    <div
      class="d-flex justify-content-between align-items-center filter-search-bar"
    >
      <SortOptions :options="state.sortOptions" @option-selected="sortItems" />
      <SearchBar @search="handleSearch" />
    </div>
    <div v-if="!state.loading">
      <div
        v-if="state.searchText !== '' && state.filteredItems.length === 0"
        class="m-5 text-center"
      >
        <h1>검색한 상품이 없습니다.</h1>
      </div>
      <div v-else class="row row-cols-1 row-cols-sm-2 row-cols-md-4 g-4">
        <div
          class="col my-4"
          v-for="(item, index) in filteredItemsByCategory"
          :key="index"
        >
          <Card :item="item" :addToCart="(id) => addToCart(id)" />
        </div>
      </div>
      <div ref="loadMoreRef" style="height: 20px"></div>
      <div v-if="state.loading" class="loading-indicator">로딩 중...</div>
    </div>
    <div
      v-else
      class="d-flex justify-content-center align-items-center"
      style="height: 30vh"
    >
      <div
        class="spinner-grow text-success"
        style="width: 50px; height: 50px"
        role="status"
      >
        <span class="sr-only">Loading...</span>
      </div>
    </div>
  </div>
</template>

<style scoped>
.loading-indicator {
  background-color: rgba(0, 0, 0, 0.5);
  font-size: 16px;
  animation: blink 1.5s linear infinite;
}

@keyframes blink {
  50% {
    opacity: 0.5;
  }
}

.filter-search-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

a {
  text-decoration: none;
  color: black;
}
</style>
