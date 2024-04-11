<script>
import Card from "@/components/Card.vue";
import axios from "axios";
import {reactive} from "vue";

const parentCategories = [
  {name: "보충제", id: 1},
  {name: "용품", id: 2},
  {name: "헬스", id: 3},
];

const childCategories = [
  {name: "보충제", id: 1, parentId: 1},
  {name: "보충제", id: 2, parentId: 1},
  {name: "보충제", id: 3, parentId: 1},
  {name: "보충제", id: 4, parentId: 1},
  {name: "보충제", id: 5, parentId: 1},
  {name: "보충제", id: 6, parentId: 2},
  {name: "보충제", id: 7, parentId: 2},
  {name: "보충제", id: 8, parentId: 2},
  {name: "보충제", id: 9, parentId: 2},
  {name: "보충제", id: 10, parentId: 3},
  {name: "보충제", id: 11, parentId: 3},
  {name: "보충제", id: 12, parentId: 3},
  {name: "보충제", id: 13, parentId: 3},
  {name: "보충제", id: 14, parentId: 3},
  {name: "보충제", id: 15, parentId: 3},
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
  components: {Card},

  setup() {
    const state = reactive({
      items: [],
    });
    axios.get("/api/item/list").then(({data}) => {
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
      parentCategories,
      childCategories,
      showChildren,
      selectedParentId,
      filterProducts,
      //   getChildCategories,
    };
  },
};
</script>

<template>
  <div class="align-middle bg-danger text-white d-flex justify-content-center align-items-center" style="height: 300px">
    <div class="text-center">
      <h1 class="display-4 fw-bolder">엘리스짐</h1>
      <h5 class="lead fw-normal text-white-50">불가능 한 것을 이루는 유일한 방법은 가능하다고 믿는 것 입니다!</h5>
    </div>
  </div>
  <div class="container">
    <div>
      <div class="dropdown my-2 d-flex justify-content-end">
        <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
          정렬 기준
        </button>
        <ul class="dropdown-menu">
          <li><a class="dropdown-item" href="#">높은 할인률순</a></li>
          <li><a class="dropdown-item" href="#">낮은 할인률순</a></li>
          <li><a class="dropdown-item" href="#">높은 가격순</a></li>
          <li><a class="dropdown-item" href="#">낮은 가격순</a></li>
        </ul>
      </div>
    </div>

    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
      <div class="col my-4" v-for="(item, idx) in state.items" :key="idx">
        <Card :item="item"/>
      </div>
    </div>
    <nav aria-label="..." style="margin-top: 150px">
      <ul class="pagination justify-content-center">
        <li class="page-item disabled">
          <span class="page-link">Previous</span>
        </li>
        <li class="page-item"><a class="page-link" href="#">1</a></li>
        <li class="page-item active" aria-current="page">
          <span class="page-link">2</span>
        </li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item">
          <a class="page-link" href="#">Next</a>
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
