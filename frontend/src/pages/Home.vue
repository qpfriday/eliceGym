<template>
  <div class="home">
    <div class="album py-5 bg-body-tertiary">
      <div class="container">
        <div class="category_main">
          <div class="category_header">
            <div class="c_text">스토어 카테고리</div>
            <div class="search-container">
              <input
                type="text"
                class="search-input"
                placeholder="검색어를 입력하세요"
              />
            </div>
          </div>

          <div class="category_list">
            <div class="parent_list">
              <div
                v-for="parentCategory in paerntCategories"
                v-bind:key="parentCategory.id"
                v-on:click="showChildren(parentCategory.id)"
              >
                <div v-bind:id="`${parentCategory.id}`" class="category">
                  {{ parentCategory.name }}
                </div>
              </div>
            </div>

            <div id="child_list" class="child_list">
              <!-- <div
                v-for="childCategory in getChildCategories(selectedParentId)"
                v-bind:key="childCategory.id"
                class="category "
              >
                {{ childCategory.name }}
              </div> -->
            </div>
          </div>
        </div>

        <div class="sort-select">
          <select id="sort-select">
            <option value="newest">최신 상품 순</option>
            <option value="sales">판매량 순</option>
          </select>
        </div>

        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
          <div class="col" v-for="(item, idx) in state.items" :key="idx">
            <Card :item="item" />
          </div>
        </div>
      </div>
    </div>

    <div class="page">
      <div class="pagination">
        <button class="prev">
          <i class="fa fa-chevron-left" aria-hidden="true"></i>
        </button>
        <button>1</button>
        <button>2</button>
        <button class="active">3</button>
        <button>4</button>
        <button>5</button>
        <button class="next">
          <i class="fa fa-chevron-right" aria-hidden="true"></i>
        </button>
      </div>
    </div>
  </div>
</template>
<script>
import Card from "@/components/Card.vue";
import axios from "axios";
import { reactive } from "vue";

const paerntCategories = [
  { name: "보충제", id: 1 },
  { name: "용품", id: 2 },
  { name: "헬스", id: 3 },
];

const childCategories = [
  { name: "보충제", id: 1, parentId: 1 },
  { name: "보충제", id: 2, parentId: 1 },
  { name: "보충제", id: 3, parentId: 1 },
  { name: "보충제", id: 4, parentId: 1 },
  { name: "보충제", id: 5, parentId: 1 },
  { name: "보충제", id: 6, parentId: 2 },
  { name: "보충제", id: 7, parentId: 2 },
  { name: "보충제", id: 8, parentId: 2 },
  { name: "보충제", id: 9, parentId: 2 },
  { name: "보충제", id: 10, parentId: 3 },
  { name: "보충제", id: 11, parentId: 3 },
  { name: "보충제", id: 12, parentId: 3 },
  { name: "보충제", id: 13, parentId: 3 },
  { name: "보충제", id: 14, parentId: 3 },
  { name: "보충제", id: 15, parentId: 3 },
];

let selectedParentId = 1;

const filterProducts = (category) => {
  console.log(category);
  // axios.get(`/api/product/list/${category}`).then(({ data }) => {
  //   state.products = data;
  // });
};
export default {
  name: "Home",
  components: { Card },

  setup() {
    const state = reactive({
      items: [],
    });
    axios.get("/api/item/list").then(({ data }) => {
      state.items = data;
    });

    const showChildren = (parentId) => {
      selectedParentId = parentId;
      const child_list = document.getElementById("child_list");
      child_list.innerHTML = "";

      childCategories
        .filter((child) => child.parentId === parentId)
        .forEach((child) => {
          child_list.innerHTML += `<div
                v-bind:key=${child.id}
                v-on:click="filterProducts(${child.id})"
                data-id=${child.id}
                id="childCategory"
                class="category childCategory btn btn-secondary"
              >
               ${child.name}
              </div>`;
        });
    };

    // const filterProducts = (category) => {
    //   console.log(category);
    //   // axios.get(`/api/product/list/${category}`).then(({ data }) => {
    //   //   state.products = data;
    //   // });
    // };

    // const getChildCategories = (parentId) => {
    //   console.log(parentId);
    //   return childCategories.filter((child) => child.parentId === parentId);
    // };

    return {
      state,
      paerntCategories,
      childCategories,
      showChildren,
      selectedParentId,
      filterProducts,
      //   getChildCategories,
    };
  },
};
</script>
<style scoped>
.sort-select {
  margin-top: 20px;
  margin-bottom: 20px;
  padding: 5px;
}
#sort-select {
  padding: 10px;
  border: 2px solid #ccc;
  border-radius: 10px;
}
.category_header {
  display: flex;
  justify-content: space-between;
  align-content: center;
  align-items: center;
}

.search-container {
  position: relative;
  margin: 20px;
}

.search-input {
  padding: 10px 40px 10px 20px;
  border: 2px solid #ccc;
  border-radius: 10px;
  width: 300px;
  transition: width 0.4s ease-in-out;
  outline: none;
}

.search-input:focus {
  width: 350px;
}
.category_list {
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: 20px;
  gap: 20px;
}
.parent_list {
  display: flex;
  justify-content: center;
  gap: 20px;
}
.child_list {
  display: flex;
  justify-content: center;
  gap: 20px;
}

.category {
  background-color: #f0f0f0;
  padding: 20px;
  text-align: center;
  width: 90px;
  border-radius: 10px;
  cursor: pointer;
}
.category:hover {
  background-color: #e0e0e0;
}

.child_list .category {
  width: 100px;
}

a {
  text-decoration: none;
  color: black;
}
.page {
  display: flex;
  justify-content: center;
  margin-top: 50px;
}
.pagination {
  display: inline-block;
  margin-bottom: 20px;
}

.pagination button {
  color: black;
  float: left;
  padding: 8px 16px;
  text-decoration: none;
  transition: background-color 0.3s;
  border: 1px solid #ddd;
  margin: 0 4px;
  cursor: pointer;
  background-color: white;
}

.pagination button.active {
  background-color: dodgerblue;
  color: white;
  border: 1px solid #4caf50;
}

.pagination button:hover:not(.active) {
  background-color: #ddd;
}

.pagination .prev,
.pagination .next {
  background-color: #f2f2f2;
  border: 1px solid #ddd;
  padding: 8px 12px;
  margin: 0 4px;
  cursor: pointer;
}

.pagination .prev:hover,
.pagination .next:hover {
  background-color: #ddd;
}
</style>
