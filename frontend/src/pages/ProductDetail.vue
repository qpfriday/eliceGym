<script>
import axios from "axios";
import { watch } from "vue";
import { useRoute } from "vue-router";
import { reactive } from "vue";
import { addCommas } from "@/scripts/lib";
import {useStore} from "vuex";
import router from "@/scripts/router";

export default {
  name: "ProductDetail",
  methods: { addCommas },
  setup() {
    const route = useRoute();
    const store = useStore();
    const itemId = route.params.itemId;

    const increaseStock = () => {
      state.quantity++;
    };

    const decreaseQuantity = () => {
      if (state.quantity > 1) {
        state.quantity--;
      }
    };

    const state = reactive({
      item: {},
      img: null,
      quantity: 1,
      loading: true,
    });

    axios.get(`/api/item/${itemId}`).then(({ data }) => {
      console.log(data);
      state.item = data;
      state.img = `data:image/jpeg;base64,` + data.img;
      state.loading = false;
    });

    const addToCart = (e) => {
      e.preventDefault();
      axios
        .post(`/api/cart/items/${itemId}?quantity=${state.quantity}`)
        .then(() => {
          alert("장바구니에 담겼습니다.");
          console.log("success");
        })
        .catch(error => {
          if (error.response && error.response.status === 401) {
            alert("장바구니에 상품을 추가하려면 로그인이 필요합니다.");
            // Redirect to the login page
            router.push({ path: "/login" });
          } else {
            console.error("장바구니에 상품을 추가하는 중 오류 발생:", error);
            alert("장바구니에 상품을 추가하는 중 오류가 발생했습니다.");
          }
        });
    };

    const deleteItem = () => {
      axios.delete(`/api/item/${itemId}/delete`)
        .then(() => {
          alert("상품이 삭제되었습니다.");
          router.push({path: "/"}); // 삭제 후 홈 페이지로 이동
        })
        .catch(error => {
          console.error("상품 삭제 실패:", error);
          alert("상품 삭제에 실패했습니다. 다시 시도해주세요.");
        });
    };

    watch(
      () => route.params.itemId,
      (itemId) => {
        console.log(itemId);
      }
    );

    return { state, increaseStock, decreaseQuantity, addToCart, deleteItem, account: store.state.account };
  },
};
</script>
<template>
  <div
    v-if="!state.loading"
    class="container"
    style="margin-top: 100px; width: 1300px"
  >
    <div class="row">
      <div class="col-6">
        <img
          :src="state.item.imgPath == null ? state.img : state.item.imgPath"
          class="img-fluid"
          alt="{{state.item.name}}"
        />
      </div>
      <div class="col-6">
        <h3>{{ state.item.name }}</h3>
        <h6 class="">상품 상세</h6>
        <div class="d-flex flex-row mb-3">
          <h4 class="p-2" style="color: #fd7e14">
            [{{ state.item.discountPer }}%]
          </h4>
          <del class="p-2">{{ state.item.price }} 원</del>
          <h4 class="p-2">
            {{
              addCommas(
                Math.round(
                  state.item.price -
                    (state.item.price * state.item.discountPer) / 100.0
                )
              )
            }}
            원
          </h4>
        </div>
        <table class="table">
          <tr>
            <th style="width: 100px">제조사</th>
            <td>Elice</td>
          </tr>
          <tr>
            <th>배송비</th>
            <td>{{ state.item.deliveryPrice }} 원</td>
          </tr>
          <tr>
            <th>옵션</th>
            <td>{{ state.item.selection }}</td>
          </tr>
          <tr>
            <th>수량</th>
            <td>
              <span class="count">
                <button
                  type="button"
                  @click="decreaseQuantity"
                  class="btn btn-outline-secondary rounded-circle"
                  style="width: 38px"
                >
                  -
                </button>
                <input
                  type="number"
                  readonly="readonly"
                  v-model="state.quantity"
                  style="
                    margin-left: 15px;
                    border: none;
                    text-align: center;
                    width: 50px;
                  "
                />
                <button
                  type="button"
                  @click="increaseStock"
                  class="btn btn-outline-secondary rounded-circle"
                  style="width: 38px"
                >
                  +
                </button>
              </span>
            </td>
          </tr>
        </table>
        <div style="text-align: end">
          <span>총 금액: </span>
          <span style="font-size: 40px">{{
            addCommas(
              Math.round(
                state.item.price -
                  (state.item.price * state.item.discountPer) / 100.0
              ) *
                state.quantity +
                state.item.deliveryPrice
            )
          }}</span>
        </div>
        <div class="container text-center" v-if="account.role === 'ROLE_ADMIN' || 'ROLE_SELLER'">
          <div class="row">
            <div class="col"></div>
            <div class="col text-end">
              <div class="d-grid gap-2">
                <a
                  href="#link"
                  class="btn btn-danger btn-lg"
                  role="button"
                  style="width: 200px"
                  @click="deleteItem"
                  >삭제하기</a>
              </div>
            </div>
            <div class="col text-end">
              <div class="d-grid gap-2">
                <router-link
                  class="btn btn-warning btn-lg"
                  :to="{ name: 'modifyItem', params: { itemId: `${state.item.id}` } }"
                  role="button"
                  style="width: 200px"
                  >수정하기</router-link>
              </div>
            </div>
          </div>
        </div>
        <div class="container text-center" v-else>
          <div class="row">
            <div class="col"></div>
            <div class="col text-end">
              <div class="d-grid gap-2">
                <a
                  href="#link"
                  class="btn btn-secondary btn-lg"
                  role="button"
                  style="width: 200px"
                  >구매하기</a>
              </div>
            </div>
            <div class="col text-end">
              <div class="d-grid gap-2">
                <a
                  class="btn btn-primary btn-lg"
                  @click="addToCart"
                  role="button"
                  style="width: 200px"
                  >장바구니 담기</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div
    v-else
    class="d-flex justify-content-center align-items-center"
    style="height: 30vh; margin-top: 200px"
  >
    <div
      class="spinner-grow text-danger"
      style="width: 50px; height: 50px"
      role="status"
    >
      <span class="sr-only">Loading...</span>
    </div>
  </div>
</template>

<style></style>
