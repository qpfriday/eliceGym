<script>
import { addCommas } from '@/scripts/lib';
import axios from "axios";
export default {
  name: "Card",
  props: {
    item: Object
  },
  setup() {
    const addToCart = (itemId) => {
      axios.post(`/api/cart/items/${itemId}`).then(() => {
        console.log('success')
      })
    };

    return {addCommas, addToCart}
  }
}

</script>

<template>
  <div class="card shadow-sm">
    <span class="img" :style="{backgroundImage: `url(${item.imgPath})`}"></span>
    <div class="card-body">
      <span class="card-text">{{ item.name }} &nbsp;</span>
      <span class="discount badge bg-danger">{{ item.discountPer }} %</span>
      <div class="d-flex justify-content-between align-items-center">
        <div class="btn btn-primary" @click="addToCart(item.id)">
          <i class="fa fa-shopping-cart" aria-hidden="true"></i>
        </div>
        <small class="price text-muted">{{ addCommas(item.price) }} 원</small>
        <small class="real text-danger">{{ addCommas(item.price - ( item.price * item.discountPer / 100)) }} 원</small>
      </div>
    </div>
  </div>
</template>

<style scoped>
  .card .img {
    display: inline-block;
    width: 100%;
    height: 250px;
    background-size: cover;
    background-position: center;
  }
  .card .card-body .price {
    text-decoration: line-through;
  }
</style>