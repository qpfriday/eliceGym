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
    <div class="product">
      <div class="img" :style="{ backgroundImage: `url(${item.imgPath})` }" />
      <!-- <img src="/img/product_test1.jpg" alt="Product 1" /> -->
      <div class="btn-cart" v-on:click="addToCart">
        <!-- <button class="btn-add-to-cart"> -->
        <i class="fa fa-shopping-cart" aria-hidden="true"></i>
        <!-- </button> -->
      </div>
      <div class="product_tag">
        <div class="product_name">
          <h4>{{ item.name }}</h4>
        </div>
        <div class="product_price">
          <p class="percentile badge bg-danger">{{ item.discountPer }}%</p>
          <p class="price">{{ addCommas(item.price) }} 원</p>
          <p class="discount_price text-danger font-weight-bold">
            {{
              addCommas(
                Math.round(item.price - (item.price * item.discountPer) / 100.0)
              )
            }}
            원
          </p>
        </div>
      </div>
    </div>
  </router-link>
</template>

<style scoped>
.btn-cart {
  position: absolute;
  top: 5px;
  right: 5px;
  width: 40px;
  height: 40px;

  z-index: 1;
}
.btn-cart:hover {
  cursor: pointer;
  color: red;
}

.btn-add-to-cart i:hover {
  color: red;
  cursor: pointer;
  z-index: 1;
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
}
.product:hover {
  box-shadow: 1px 1px 1px 1px gray;
}

.product img {
  max-width: 100%;
  height: auto;
}

.product h4 {
  margin-top: 10px;
  margin-bottom: 0px;
}

.btn-add-to-cart {
  background-color: transparent;
  color: dodgerblue;
  border: none;
  cursor: pointer;
}
</style>
