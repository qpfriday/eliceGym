<script>
import { computed, reactive, ref } from "vue";
import axios from "axios";
import { addCommas } from "@/scripts/lib";
import router from "@/scripts/router";

export default {
  setup() {
    const state = reactive({
      items: [],
      user: {
        name: "",
        phoneNumber: "",
        deliveryAddress: "",
      },
      form: {
        name: "",
        phoneNumber: "",
        postCode: "",
        address1: "",
        address2: "",
        request: "",
        payment: "",
        cardNumber: "",
        items: "",
      },
      loading: true,
    });
    const load = () => {
      axios.get("/api/cart/items").then(({ data }) => {
        state.items = data;
        state.loading = false;
      });

      axios.get("/api/account/user").then(({ data }) => {
        state.user = data;
      });
    };

    const validateForm = () => {
      const {
        name,
        phoneNumber,
        address1,
        address2,
        postCode,
        payment,
        cardNumber,
      } = state.form;
      return (
        name &&
        phoneNumber &&
        address1 &&
        payment &&
        cardNumber &&
        postCode &&
        address2
      );
    };

    const computedPrice = computed(() => {
      return state.items.reduce((acc, item) => {
        return acc + (item.price - (item.price * item.discountPer) / 100);
      }, 0);
    });

    const fillUserInfo = (event) => {
      if (event.target.checked && Object.keys(state.user).length !== 0) {
        const user = state.user;
        state.form.name = user.name;
        state.form.phoneNumber = user.phoneNumber;
        state.form.address = user.deliveryAddress;
      } else {
        // Clear form fields if unchecked or if user data is not available
        state.form.name = "";
        state.form.phoneNumber = "";
        state.form.address = "";
      }
    };
    const showCustomRequest = ref(false);

    const purchase = () => {
      console.log(state.form);

      if (validateForm()) {
        state.form.items = JSON.stringify(state.items);
        axios
          .post("api/orders", state.form)
          .then(() => {
            router.push({ path: "/orders" });
            window.alert("주문을 완료하였습니다.");
          })
          .catch(() => {
            window.alert("주문에 실패하였습니다. 다시 시도해주세요.");
          });
      } else {
        window.alert("모든 필드를 올바르게 입력해 주세요.");
      }
    };

    load();
    //주소 api
    const openPostCode = () => {
      new window.daum.Postcode({
        oncomplete: function (data) {
          var roadAddr = data.roadAddress;

          document.getElementById("postcode").value = data.zonecode;
          document.getElementById("roadAddress").value = roadAddr;
          document.getElementById("jibunAddress").value = data.jibunAddress;

          state.form.address1 = roadAddr;
          state.form.postCode = data.zonecode;
        },
        theme: {},
      }).open({
        left: -1300,
        top: 200,
      });

      //창 닫기
    };

    return {
      state,
      addCommas,
      computedPrice,
      purchase,
      fillUserInfo,
      showCustomRequest,
      openPostCode,
    };
  },
};
</script>

<template>
  <div class="order">
    <div class="container">
      <main>
        <div class="py-5 text-center"><h2>주문하기</h2></div>
        <div class="row g-5">
          <div class="col-md-7 col-lg-4 order-md-last">
            <h4 class="d-flex justify-content-between align-items-center mb-3">
              <span class="text-primary">장바구니 목록</span>
              <span class="badge bg-primary rounded-pill">{{
                state.items.length
              }}</span>
            </h4>
            <div v-if="!state.loading" class="container">
              <ul class="list-group mb-3">
                <li
                    class="list-group-item d-flex justify-content-between lh-sm"
                    v-for="(i, idx) in state.items"
                    :key="idx"
                >
                  <div>
                    <h6 class="my-0">{{ i.name }}</h6>
                  </div>
                  <span class="text-body-secondary">
                  {{ addCommas(i.price - (i.price * i.discountPer) / 100) }} 원
                </span>
                </li>
              </ul>
              <h2 class="text-center total-price">
                {{ addCommas(computedPrice) }} 원
              </h2>
            </div>
            <div v-else class="d-flex justify-content-center align-items-center" style="height: 30vh;">
              <div class="spinner-grow text-primary" style="width: 50px; height: 50px;" role="status">
                <span class="sr-only">Loading...</span>
              </div>
            </div>
          </div>

          <div class="col-md-5 col-lg-8" id="layer">
            <h4 class="mb-3">주문 정보</h4>
            <div class="form-check">
              <input
                class="form-check-input"
                type="checkbox"
                value=""
                id="fillUserInfo"
                @change="fillUserInfo"
              />
              <label class="form-check-label" for="fillUserInfo">
                주문 고객 정보와 동일
              </label>
            </div>
            <form
              class="needs-validation"
              novalidate=""
              @submit.prevent="purchase"
            >
              <div class="row g-3">
                <div class="col-12">
                  <label for="username" class="form-label">이름</label>
                  <input
                    type="text"
                    required
                    class="form-control"
                    id="username"
                    placeholder="Username"
                    v-model="state.form.name"
                  />
                </div>
                <div class="col-12">
                  <label for="phoneNumber" class="form-label">전화번호</label>
                  <input
                    type="text"
                    required
                    class="form-control"
                    id="phoneNumber"
                    placeholder="XXX-XXXX-XXXX"
                    v-model="state.form.phoneNumber"
                  />
                </div>
                <div class="col-12 address-fields">
                  <label for="address" class="form-label">주소</label>

                  <input
                    type="text"
                    id="postcode"
                    placeholder="우편번호"
                    class="form-control"
                  />
                  <!-- <input
                    type="button"
                    @click="openPostCode"
                    value="우편번호 찾기"
                  /><br /> -->
                  <button
                    class="btn btn-primary btn-sm"
                    style="padding: 10px"
                    type="button"
                    @click="openPostCode"
                  >
                    우편번호 찾기
                  </button>
                  <input
                    type="text"
                    class="form-control"
                    id="roadAddress"
                    placeholder="도로명주소"
                  />
                  <input
                    type="text"
                    id="jibunAddress"
                    placeholder="지번주소"
                    class="form-control"
                  />
                  <span id="guide" style="color: #999; display: none"></span>
                  <input
                    type="text"
                    class="form-control"
                    id="detailAddress"
                    placeholder="상세주소"
                    v-model="state.form.address2"
                  />

                  <!-- <input
                    @click="openPostCode"
                    type="text"
                    required
                    class="form-control"
                    id="address"
                    placeholder="Address"
                    v-model="state.form.address"
                  /> -->
                </div>
                <div class="col-12">
                  <label for="request" class="form-label">배송 요청사항</label>
                  <div class="form-check">
                    <input
                      class="form-check-input"
                      type="checkbox"
                      value=""
                      id="showCustomRequest"
                      @click="showCustomRequest = !showCustomRequest"
                    />
                    <label class="form-check-label" for="showCustomRequest"
                      >직접 입력하기</label
                    >
                  </div>
                  <!-- 선택 사항을 나타내는 div -->
                  <div v-if="showCustomRequest">
                    <input
                      type="text"
                      class="form-control"
                      id="customRequest"
                      placeholder="배송 요청사항을 입력하세요 (100자 이내)"
                      required
                      v-model="state.form.request"
                    />
                  </div>
                  <div v-else>
                    <select
                      class="custom-select"
                      v-model="state.form.request"
                      required
                    >
                      <option selected>문앞에 두고 가주세요</option>
                      <option value="경비실에 맡겨주세요">
                        경비실에 맡겨주세요
                      </option>
                      <option value="직접 전달해주세요">
                        직접 전달해주세요
                      </option>
                    </select>
                  </div>
                </div>
              </div>
              <hr class="my-4" />
              <h4 class="mb-3">결제 수단</h4>
              <div class="my-3">
                <div class="custom-control custom-radio">
                  <input
                    id="card"
                    type="radio"
                    class="custom-control-input"
                    checked
                    required
                    v-model="state.form.payment"
                    value="card"
                  />
                  <label class="custom-control-label" for="card"
                    >신용카드</label
                  >
                </div>
                <div class="custom-control custom-radio">
                  <input
                    id="bank"
                    type="radio"
                    class="custom-control-input"
                    required
                    v-model="state.form.payment"
                    value="bank"
                  />
                  <label class="custom-control-label" for="bank"
                    >무통장입금</label
                  >
                </div>
              </div>
              <label for="cc-number" class="form-label">카드 번호</label>
              <input
                type="text"
                class="form-control"
                id="cc-number"
                v-model="state.form.cardNumber"
                required
              />
              <hr class="my-4" />
              <button class="w-100 btn btn-primary btn-lg" type="submit">
                결제하기
              </button>
            </form>
          </div>
        </div>
      </main>
    </div>
  </div>
</template>

<style scoped>
.col-12 input {
  margin-bottom: 10px;
}
.address-fields {
  display: flex;
  gap: 5px;
  flex-wrap: wrap;
}
.address-fields label {
  width: 100%;
  display: block;
}
#postcode,
#roadAddress,
#jibunAddress,
#detailAddress {
  width: 48%;
  padding: 10px 10px;
  margin: 8px 0;
  box-sizing: border-box;
}
</style>
