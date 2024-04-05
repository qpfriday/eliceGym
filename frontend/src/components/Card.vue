<script>
import { addCommas } from "@/scripts/lib";
import axios from "axios";
export default {
  name: "Card",
  props: {
    item: {
      type: Object,
      required: true,
    },
  },
  setup() {
    const addToCart = (itemId) => {
      axios.post(`/api/cart/items/${itemId}`).then(() => {
        console.log("success");
      });
    };

    return { addCommas, addToCart };
  },
};
</script>

<template>
  <!-- <div class="card shadow-sm">
    <span
      class="img"
      :style="{ backgroundImage: `url(/img/protein.png)` }"
    ></span>
    <div class="card-body">
      <span class="card-text">{{ product.name }} &nbsp;</span>
      <span class="discount badge bg-danger">{{ product.discountPer }} %</span>
      <div class="d-flex justify-content-between align-items-center">
        <div class="btn btn-primary" @click="addToCart(product.id)">
          <i class="fa fa-shopping-cart" aria-hidden="true"></i>
        </div>
        <small class="price text-muted"
          >{{ addCommas(product.price) }} 원</small
        >
        <small class="real text-danger"
          >{{
            addCommas(
              product.price - (product.price * product.discountPer) / 100
            )
          }}
          원</small
        >
      </div>
    </div>
  </div> -->

  <router-link
    :to="{ name: 'productDetail', params: { itemId: `${item.id}` } }"
  >
    <div class="product">
      <span class="img" :style="{ backgroundImage: `url(/img/protein.png)` }" />
      <!-- <img src="/img/product_test1.jpg" alt="Product 1" /> -->
      <button class="btn-add-to-cart">
        <i class="fa fa-shopping-cart" aria-hidden="true"></i>
      </button>
      <div class="product_tag">
        <div class="product_name">
          <h4>{{ item.name }}</h4>
        </div>
        <div class="product_price">
          <p class="percentile badge bg-danger">{{ item.discountPer }}%</p>
          <p class="price">{{ addCommas(item.price) }} 원</p>
          <p class="discount_price text-danger font-weight-bold">
            {{ addCommas(item.price - (item.price * item.discountPer) / 100) }}
            원
          </p>
        </div>
      </div>
    </div>
  </router-link>
</template>

<style scoped>
/* .card .img {
  display: inline-block;
  width: 100%;
  height: 250px;
  background-size: cover;
  background-position: center;
}
.card .card-body .price {
  text-decoration: line-through;
} */
.btn-add-to-cart i:hover {
  color: red;
}
.product_price {
  display: flex;
  margin-left: 10px;
  margin-top: 10px;
  gap: 20px;
}
.product .price {
  font-weight: bold;
  font-size: 15px;
  margin-bottom: 0px;
}
.price {
  text-decoration: line-through;
}
.img {
  display: inline-block;
  height: 250px;
  width: 100%;
  background-size: cover;
  background-position: center;
}

.product {
  border: 1px solid #ddd;
  position: relative;
  text-align: center;
  padding-bottom: 20px;
  border-radius: 6px;
  cursor: pointer;
}
.product:hover {
  box-shadow: 1px 2px 2px 1px gray;
}

.product img {
  max-width: 100%;
  height: auto;
}

.product h4 {
  margin-top: 10px;
  margin-bottom: 0px;
}

.product .btn-add-to-cart {
  position: absolute;
  top: 5px;
  right: 5px;
  background-color: transparent;
  color: dodgerblue;
  border: none;
  cursor: pointer;
}
</style>
