<script>
import {reactive} from "vue";
import axios from "axios";
import {addCommas} from "@/scripts/lib";

export default {
  setup() {
    const state = reactive({
      items: []
    })
    const load = () => {
      axios.get("/api/cart/items").then(({data}) => {
        console.log(data);
        state.items = data;
      })
    }
    const remove = (itemId) => (
        axios.delete(`/api/cart/items/${itemId}`).then(() => {
          load();
        })
    );

    load();

    return {state, addCommas, remove}
  },
}
</script>

<template>
  <div class="cart">
    <div class="container">
      <div class="py-5 text-center"><h2>내 장바구니</h2></div>
      <ul>
        <li v-for="(i, idx) in state.items" :key="idx">
          <img :src="i.imgPath"/>
          <span class="name">{{ i.name }}</span>
          <span class="price">{{ addCommas(i.price - i.price * i.discountPer / 100) }} 원</span>
          <i class="fa fa-trash" @click="remove(i.id)"></i>
        </li>
      </ul>
      <div v-if="state.items.length > 0">
        <router-link to="/" class="btn btn-light" style="border: solid 0.5px #ccc; margin-top: 20px">쇼핑 더 하러가기</router-link>
        <router-link to="/order" class="btn btn-primary" style="margin-top: 20px">구입하기</router-link>
      </div>
      <div v-else>
        <h1 class="text-center">장바구니가 비었습니다</h1>
        <router-link to="/" class="btn btn-primary">쇼핑하러 가기</router-link>
      </div>
    </div>
  </div>
</template>

<style scoped>
.cart ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

.cart ul li {
  border: 1px solid #eee;
  margin-top: 25px;
  margin-bottom: 25px;
}

.cart ul li img {
  width: 150px;
  height: 150px;
}

.cart ul li .name {
  margin-left: 25px;
}

.cart ul li .price {
  margin-left: 25px;
}

.cart ul li i {
  float: right;
  font-size: 20px;
  margin: 65px 50px;
}

.cart .btn {
  width: 300px;
  padding: 30px 50px;
  font-size: 20px;
  display: block;
  margin: 0 auto;
}
.cart h1 {
  margin: 100px;
}
</style>