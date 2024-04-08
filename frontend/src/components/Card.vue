<script>
import { addCommas } from "@/scripts/lib";
import axios from "axios";
//import { ref } from "vue";
export default {
  name: "Card",
  props: {
    item: {
      type: Object,
      required: true,
    },
  },
  setup(props) {
    const addToCart = (e) => {
      e.preventDefault();
      const itemId = props.item.id;
      console.log(itemId);

      axios.post(`/api/cart/items/${itemId}`).then(() => {
        alert("장바구니에 추가되었습니다.");
        console.log("success");
      });
    };

    return { addCommas, addToCart };
  },
};
</script>

<template>
  <router-link
    :to="{ name: 'productDetail', params: { itemId: `${item.id}` } }"
  >

    <div class="card h-100">
      <div class="badge bg-danger text-white position-absolute" style="top: 0.5rem; right: 0.5rem">{{ item.discountPer }}%</div>
      <div class="img" :style="{ backgroundImage: `url(${item.imgPath})` }" />
      <div class="card-body p-4">
        <div class="text-center">
          <h5 class="fw-bolder">{{ item.name }}</h5>
          <span class="text-secondary text-decoration-line-through">{{ addCommas(item.price) }} 원</span>
          <h4>{{addCommas(Math.round(item.price - (item.price * item.discountPer) / 100.0))}} 원</h4>
        </div>
      </div>
      <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
        <div class="text-center"><a class="btn btn-outline-dark mt-auto" v-on:click="addToCart">장바구니 추가</a></div>
      </div>
    </div>
  </router-link>
</template>

<style scoped>
.img {
  display: inline-block;
  height: 300px;
  width: 100%;
  background-size: cover;
  background-position: center;
}

.product img {
  max-width: 100%;
  height: auto;
}

</style>
