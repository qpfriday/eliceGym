<script>
import {reactive} from "vue";
import axios from "axios";
import {addCommas} from "@/scripts/lib";

export default {
  setup() {
    const state = reactive({
<<<<<<< HEAD
      items: [],
      loading: true,
=======
      carts: [],
      items: []
>>>>>>> origin/dev
    })

    const load = () => {
      axios.get("/api/cart/items").then(({data}) => {
        console.log(data);
<<<<<<< HEAD
        state.items = data;
        state.loading = false;
=======
        state.carts = data;
        makeJSON()
        console.log(state.items)
>>>>>>> origin/dev
      })
    }

    const makeJSON = () => {
      state.form = [];
      state.carts.forEach(cart => {
        axios.get(`/api/item/${cart.itemId}`).then(({data}) => {
          const item = data;
          const detail = {
            id: cart.itemId,
            quantity: cart.quantity,
            price: item.price,
            discountPer: item.discountPer,
            deliveryPrice: item.deliveryPrice,
            name: item.name,
            imgPath: item.imgPath
          }
          state.items.push(detail)
        })
      })
    }

    const remove = (itemId) => (
        axios.delete(`/api/cart/items/${itemId}`).then(() => {
          load();
        })
    )


    load()
    return {state, addCommas, remove}
  },
}
</script>

<template>
  <div v-if="!state.loading" class="cart">
    <div class="container">
      <div class="py-5 text-center"><h2>[내 장바구니]</h2></div>
      <ul>
        <li v-for="(i, idx) in state.items" :key="idx">
          <img :src="i.imgPath"/>
          <span class="name">{{ i.name }}</span>
          <span class="price">{{ addCommas(i.price - i.price * i.discountPer / 100) }} 원</span>
          <span class="quantity">{{i.quantity}}개</span>
          <i class="fa fa-trash" @click="remove(i.id)"></i>
        </li>
      </ul>
      <div v-if="state.items.length > 0">
        <router-link to="/" class="btn btn-light" style="border: solid 0.5px #ccc; margin-top: 20px">쇼핑 더 하러가기</router-link>
        <router-link :to="{ path: '/order', query: { items: JSON.stringify(state.items) }}"
                     class="btn btn-primary" style="margin-top: 20px">구입하기</router-link>
      </div>
      <div v-else>
        <h1 class="text-center">장바구니가 비었습니다</h1>
        <router-link to="/" class="btn btn-primary">쇼핑하러 가기</router-link>
      </div>
    </div>
  </div>
  <div v-else class="d-flex justify-content-center align-items-center" style="height: 30vh;">
    <div class="spinner-grow text-danger" style="width: 50px; height: 50px;" role="status">
      <span class="sr-only">Loading...</span>
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

.cart ul li .quantity {
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