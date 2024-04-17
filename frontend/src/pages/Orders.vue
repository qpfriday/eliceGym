<script>
import { reactive } from "vue";
import axios from "axios";
import { addCommas, formatDate, formatTime } from "@/scripts/lib";

export default {
  setup() {
    const state = reactive({
      orders: [],
    });

    axios.get("/api/orders").then(({data}) => {
      console.log(data);
      state.orders.push(...data);
    });

    const getItems = (order) => {
      return JSON.parse(order.items)
    }

    return {state, getItems, addCommas, formatDate, formatTime};
  },
};
</script>

<template>
  <div class="orders">
    <div class="container">
      <table class="table table-bordered">
        <thead>
        <tr>
          <th style="width: 100px">주문일자</th>
          <th style="width: 400px">주문 상품</th>
          <th style="width: 100px">상품금액 수량</th>
          <th style="width: 100px">배송비</th>
          <th style="width: 100px">주문 상태</th>
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
            <td>구매확정</td>
          </tr>
        </template>
        </tbody>
      </table>
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
