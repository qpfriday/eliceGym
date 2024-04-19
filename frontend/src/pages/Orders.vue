<script>
import { reactive } from "vue";
//import axios from "axios";
import { addCommas, formatDate, formatTime } from "@/scripts/lib";
import baseURL from "@/scripts/baseURL";

export default {
  setup() {
    const state = reactive({
      orders: [],
      loading: true,
    });

    baseURL.get("/api/orders").then(({ data }) => {
      console.log(data[0].items);
      state.orders.push(...data);
      state.loading = false;
    });

    const getItems = (order) => {
      return JSON.parse(order.items);
    };
    const calculateOrderTotal = (order) => {
      let total = 0;
      for (const item of getItems(order)) {
        total +=
            (item.price - (item.price * item.discountPer) / 100) * item.quantity +
            item.deliveryPrice;
      }
      return total;
    };

    return {
      state,
      getItems,
      addCommas,
      formatDate,
      formatTime,
      calculateOrderTotal,
    };
  },
};
</script>

<template>
  <div class="orders" v-if="!state.loading">
    <div class="container">
      <table class="table table-bordered">
        <thead>
          <tr>
            <th style="width: 100px">주문일자</th>
            <th style="width: 400px">주문 상품</th>
            <th style="width: 100px">상품금액 수량</th>
            <th style="width: 100px">배송비</th>
            <th style="width: 100px">총금액</th>
          </tr>
        </thead>
        <tbody style="font-size: small; text-align: center">
          <template v-for="(o, idx1) in state.orders" :key="idx1">
            <tr v-for="(i, idx2) in getItems(o)" :key="idx2">
              <td :rowspan="getItems(o).length" v-if="idx2 === 0">
                <div>{{ formatDate(o.createdAt) }}</div>
                <div>{{ formatTime(o.createdAt) }}</div>
              </td>
              <td style="text-align: left">
                <img
                  style="width: 50px; height: 50px; margin-right: 10px"
                  :src="i.imgPath == null ? i.img : i.imgPath"
                />
                {{ i.name }}
              </td>
              <td>
                <div>{{ addCommas(i.price) }}원</div>
                <div>({{ i.quantity }}개)</div>
              </td>
              <td>{{ addCommas(i.deliveryPrice) }}원</td>
              <td :rowspan="getItems(o).length" v-if="idx2 === 0">
                <h5>{{ addCommas(calculateOrderTotal(o)) }}원</h5>
              </td>
            </tr>
          </template>
        </tbody>
      </table>
    </div>
  </div>

  <div
    v-else
    class="d-flex justify-content-center align-items-center"
    style="height: 30vh; margin-top: 200px"
  >
    <div
      class="spinner-grow text-success"
      style="width: 50px; height: 50px"
      role="status"
    >
      <span class="sr-only">Loading...</span>
    </div>
  </div>
</template>

<style scoped>
.table {
  margin-top: 30px;
  text-align: center;
}

.table > tbody {
  border-top: 1px solid #eee;
}

.table > tbody > tr > td {
  vertical-align: middle;
}
</style>
