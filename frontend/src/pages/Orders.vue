<script>
import { reactive } from "vue";
import axios from "axios";

export default {
  setup() {
    const state = reactive({
      orders: [],
    });

    axios.get("/api/orders").then(({ data }) => {
      console.log(data);
      state.orders.push(...data);
    });

    return { state };
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
          <!-- <tr v-for="(o, idx1) in state.orders" :key="idx1">
            <td>{{ state.orders.length - idx1 }}</td>
            <td>{{ o.name }}</td>
            <td>{{ o.phoneNumber }}</td>
            <td>{{ o.address }}</td>
            <td>{{ o.request }}</td>
          </tr> -->

          <tr>
            <td rowspan="2">2024-03-03</td>
            <td style="text-align: left; margin-right: 10px">
              <img
                style="width: 50px; height: 50px; margin-right: 10px"
                src="/img/protein.png"
              />
              <span class="name">상품이름</span>
            </td>
            <td>
              <div>9,000원</div>
              <div>(1개)</div>
            </td>
            <td>무료</td>
            <td>구매확정</td>
          </tr>
          <tr>
            <td style="text-align: left">
              <img
                style="width: 50px; height: 50px; margin-right: 10px"
                src="/img/chicken.png"
              />
              <span class="name">상품이름</span>
            </td>
            <td>
              <div>9,000원</div>
              <div>(1개)</div>
            </td>
            <td>무료</td>
            <td>구매확정</td>
          </tr>
          <tr>
            <td>2024-03-04</td>
            <td style="text-align: left">
              <img
                style="width: 50px; height: 50px; margin-right: 10px"
                src="/img/shorts.png"
              />
              <span class="name">상품이름</span>
            </td>
            <td>
              <div>9,000원</div>
              <div>(1개)</div>
            </td>
            <td>3000원</td>
            <td>구매확정</td>
          </tr>
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
