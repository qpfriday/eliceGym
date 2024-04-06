<script>
import axios from "axios";
import { watch } from "vue";
import { useRoute } from "vue-router";
import { reactive } from "vue";
import {addCommas} from "../scripts/lib";

export default {
  name: "ProductDetail",
  methods: {addCommas},
  setup() {
    const route = useRoute();
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
      quantity: 1,
    });

    axios.get(`/api/item/${itemId}`).then(({ data }) => {
      console.log(data);
      state.item = data;
    });

    const addToCart = (e) => {
      e.preventDefault();

      //state.quantity int로 변환

      axios
        .post(`/api/cart/items/${itemId}?quantity=${state.quantity}`)
        .then(() => {
          alert("장바구니에 담겼습니다.");
          console.log("success");
        });
    };

    watch(
      () => route.params.itemId,
      (itemId) => {
        console.log(itemId);
      }
    );

    return { state, increaseStock, decreaseQuantity, addToCart };
  },
};
</script>
<template>
  <div class="container">
    <div class="container" id="container">
      <div class="detail_main">
        <div class="section_view">
          <div class="section_left">
            <div class="thumb">
            </div>
          </div>
          <div class="section_right">
            <div class="detail_title">
              <div class="detail_title_main">
                <span>{{ state.item.name }}</span>
                <button class="admin_product_btn">상품 수정</button>
              </div>
              <span class="short_desc">상품 짧은 소개란</span>
            </div>
            <div class="detail_price">
              <span class="price">
                <span class="per">[{{ state.item.discountPer }}%] &nbsp;</span>
                <del>{{ state.item.price }} 원</del>
                <span> &nbsp;{{
                    addCommas(Math.round(state.item.price - (state.item.price * state.item.discountPer) / 100.0))
                  }} 원</span>
              </span>
            </div>

            <div class="detail_seller">
              <div class="detail_seller_title">
                <span>제조사</span>
              </div>
              <div class="detail_seller_desc">
                <p class="first">Elice</p>
              </div>
            </div>

            <div class="detail_unit">
              <div class="detail_unit_title">
                <span>배송비</span>
              </div>
              <div class="detail_unit_desc">
                <p class="first">{{ state.item.deliveryPrice }}</p>
              </div>
            </div>

            <div class="detail_weight">
              <div class="detail_weight_title">
                <span>중량/용량</span>
              </div>
              <div class="detail_weight_desc">
                <p class="first">중량/용량 입력</p>
              </div>
            </div>

            <div class="detail_expir_date">
              <div class="detail_expir_date_title">
                <span>소비기한</span>
              </div>
              <div class="detail_expir_date_desc">
                <p class="first">소비기한 입력</p>
              </div>
            </div>

            <div class="detail_product_select">
              <div class="detail_product_select_title">
                <span>상품선택</span>
              </div>
              <div class="detail_product_select_desc">
                <div class="option">
                  <span class="count">
                    <button
                      type="button"
                      @click="decreaseQuantity"
                      class="btn down on"
                    >
                      수량내리기
                    </button>
                    <input
                      type="number"
                      readonly="readonly"
                      v-model="state.quantity"
                      class="inp"
                    />
                    <button
                      type="button"
                      @click="increaseStock"
                      class="btn up on"
                    >
                      수량올리기
                    </button>
                  </span>
                </div>
              </div>
            </div>

            <div class="detail_total_price">
              <div class="detail_total_price_main">
                <span
                  >총 상품금액:
                  <span class="won" id="won">{{
                      addCommas(Math.round(state.item.price - (state.item.price * state.item.discountPer) / 100.0) * state.quantity + state.item.deliveryPrice)
                  }}</span>
                  <span>원</span>
                </span>
              </div>
            </div>

            <div class="purchase_button">
              <div class="sec_btn">
                <button type="button" class="btn btn_purchase">
                  <a href="OrderPage.html">구매하기</a>
                </button>
              </div>
              <div class="third_btn">
                <button type="button" @click="addToCart" class="btn btn_cart">
                  <a href="#">장바구니 담기</a>
                </button>
              </div>
            </div>
          </div>
        </div>

        <div class="good-infomations">
          <div class="good_reviews" id="goods-review">
            <div class="gr_title">
              <h2>상품 후기</h2>
            </div>

            <div class="good_review_count">
              <div class="gr_count_total">
                <span>총 0개</span>
              </div>
              <div class="gr_order_by">
                <div class="order_first">
                  <a href="#" class="gr_order_btn active">추천순</a>
                </div>
                <span>|</span>
                <div class="order_second">
                  <a href="#" class="gr_order_btn">최근등록순</a>
                </div>
              </div>
            </div>

            <div class="good_review_account">
              <div class="gr_a_name">
                <span class="gr_a_n_grade">회원</span>
                <span class="gr_a_n_username">엘**</span>
              </div>

              <div class="gr_a_content">
                <div class="gr_a_product_name">[리뷰 상품 이름] 상품 이름</div>
                <div class="gr_a_content_detail">
                  <p>
                    리뷰 내용 입니다. 리뷰 내용 입니다. 리뷰 내용 입니다. 리뷰
                    내용 입니다. 리뷰 내용 입니다. 리뷰 내용 입니다. 리뷰 내용
                    입니다. 리뷰 내용 입니다. 리뷰 내용 입니다.
                  </p>
                </div>
                <div class="gr_a_content_imgs">
                  <button class="gr_a_content_img gr_thumb">
                    <img src="/img/protein.png" />
                  </button>
                </div>
                <div class="gr_a_content_date">
                  <div>
                    <span>2021.01.01</span>
                  </div>
                  <div>
                    <button class="gr_a_content_thumb">
                      <span class="gr_a_c_thumb_icon">
                        <img src="/img/review_thumbsup.svg" />
                      </span>
                      <span>도움돼요</span>
                    </button>
                  </div>
                </div>
              </div>
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
html body {
  margin: 0;
  padding: 0;
  font-family: "Roboto", sans-serif;
  font-size: 16px;
  line-height: 1.5;
  color: #333;
  background-color: #fff;
}

.container {
  width: 100%;
  max-width: 1440px;
  margin: 0 auto;
}

.detail_main {
  width: 100%;
  margin: 20px auto;
  display: flex;
  flex-direction: column;
}

.detail_main .section_view {
  width: 1050px;
  margin: 0 auto;
  display: flex;
  padding-top: 20px;
  height: 885px;
  justify-content: space-between;
}

.section_view .section_left {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.section_view .section_right {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.section_left .thumb {
  background-repeat: no-repeat;
  background-size: cover;
}


.section_right {
  width: 560px;
}

.section_right > div {
  width: 100%;
}
.detail_title {
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
  font-weight: 500;
  font-size: 24px;
  color: rgb(51, 51, 51);
  line-height: 34px;
  letter-spacing: -0.5px;
}
.detail_title_main {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.admin_product_btn {
  height: 50px;
  width: 100px;
  margin-right: 0px;
  padding-bottom: 2px;
  border: 1px solid #000000;
  border-radius: 4px;
  background: #fff;
  font-weight: 700;
  font-size: 16px;
  cursor: pointer;
  text-align: center;
}


.short_desc {
  padding: 4px 60px 0 0;
  font-size: 14px;
  color: #999;
  line-height: 20px;
  word-break: break-all;
}

.detail_price {
  display: flex;
  flex-direction: column;
}

.detail_price .price {
  font-weight: 600;
  font-size: 28px;
  color: rgb(51, 51, 51);
}

.detail_price .price .per {
  font-size: 28px;
  color: rgb(250, 98, 47);
}


.detail_delivery_title span {
  font-size: 14px;
}

.detail_delivery_desc p {
  width: 100%;
  margin: 0px;
}

.detail_delivery_desc .first {
  color: rgb(51, 51, 51);
  font-weight: 400;
  line-height: 19px;
  white-space: pre-line;
  word-break: break-all;
  overflow: hidden;
}

.detail_seller {
  width: 100%;
  display: flex;
  /* height: 20px; */
  padding-top: 17px;
  padding-bottom: 17px;
  flex-direction: row;
  border-bottom: 1px solid #e5e5e5;
}

.detail_seller .detail_seller_title {
  align-items: flex-start;
  width: 128px;
}

.detail_seller_title span {
  font-size: 14px;
}

.detail_seller_desc p {
  width: 100%;
  margin: 0px;
}

.detail_seller_desc .first {
  color: rgb(51, 51, 51);
  font-weight: 400;
  line-height: 19px;
  white-space: pre-line;
  word-break: break-all;
  overflow: hidden;
}

.detail_pack_type_title span {
  font-size: 14px;
}

.detail_pack_type_desc p {
  width: 100%;
  margin: 0px;
}

.detail_pack_type_desc .first {
  color: rgb(51, 51, 51);
  font-weight: 400;
  line-height: 19px;
  white-space: pre-line;
  word-break: break-all;
  overflow: hidden;
}
.detail_unit {
  width: 100%;
  display: flex;
  /* height: 20px; */
  padding-top: 17px;
  padding-bottom: 17px;
  flex-direction: row;
  border-bottom: 1px solid #e5e5e5;
}

.detail_unit .detail_unit_title {
  align-items: flex-start;
  width: 128px;
}

.detail_unit_title span {
  font-size: 14px;
}

.detail_unit_desc p {
  width: 100%;
  margin: 0px;
}

.detail_unit_desc .first {
  color: rgb(51, 51, 51);
  font-weight: 400;
  line-height: 19px;
  white-space: pre-line;
  word-break: break-all;
  overflow: hidden;
}

.detail_weight {
  width: 100%;
  display: flex;
  /* height: 20px; */
  padding-top: 17px;
  padding-bottom: 17px;
  flex-direction: row;
  border-bottom: 1px solid #e5e5e5;
}

.detail_weight .detail_weight_title {
  align-items: flex-start;
  width: 128px;
}

.detail_weight_title span {
  font-size: 14px;
}

.detail_weight_desc p {
  width: 100%;
  margin: 0px;
}

.detail_weight_desc .first {
  color: rgb(51, 51, 51);
  font-weight: 400;
  /* line-height: 19px; */
  white-space: pre-line;
  word-break: break-all;
  overflow: hidden;
}

.detail_expir_date {
  width: 100%;
  display: flex;
  /* height: 20px; */
  padding-top: 17px;
  padding-bottom: 17px;
  flex-direction: row;
  border-bottom: 1px solid #e5e5e5;
}

.detail_expir_date .detail_expir_date_title {
  align-items: flex-start;
  width: 128px;
}

.detail_expir_date_title span {
  font-size: 14px;
}

.detail_expir_date_desc p {
  width: 100%;
  margin: 0px;
}

.detail_expir_date_desc .first {
  color: rgb(51, 51, 51);
  font-weight: 400;
  line-height: 19px;
  white-space: pre-line;
  word-break: break-all;
  overflow: hidden;
}

.detail_product_select {
  width: 100%;
  display: flex;
  /* height: 20px; */
  padding-top: 17px;
  padding-bottom: 17px;
  flex-direction: row;
  border-bottom: 1px solid #e5e5e5;
}

.detail_product_select .detail_product_select_title {
  align-items: flex-start;
  width: 128px;
}

.detail_product_select_title span {
  font-size: 14px;
}

.detail_product_select_desc p {
  width: 100%;
  margin: 0px;
}

.detail_product_select_desc .first {
  color: rgb(51, 51, 51);
  font-weight: 400;
  line-height: 19px;
  white-space: pre-line;
  word-break: break-all;
  overflow: hidden;
}

.detail_product_select_desc .option {
  padding-top: 0;
}

.detail_product_select_desc .count {
  overflow: hidden;
  float: left;
  width: 88px;
  /* height: 30px; */
  border: 1px solid #dddfe1;
  border-radius: 3px;
}

.detail_product_select_desc .down.on {
  background-image: url(https://res.kurly.com/pc/ico/2010/ico_minus_on.svg);
}

.detail_product_select_desc .down {
  background: #fff url(https://res.kurly.com/pc/ico/2010/ico_minus.svg)
    no-repeat 50% 50%;
  background-size: 30px 30px;
}

.detail_product_select_desc .inp {
  float: left;
  width: 30px;
  height: 30px;
  margin-right: -1px;
  padding: 0 0 4px;
  border: 0;
  background-color: #fff;
  font-size: 14px;
  color: #000;
  line-height: 18px;
  text-align: center;
}

.detail_product_select_desc .up.on {
  background-image: url(https://res.kurly.com/pc/ico/2010/ico_plus_on.svg);
}
.detail_product_select_desc .up {
  float: right;
  background: #fff url(https://res.kurly.com/pc/ico/2010/ico_plus.svg) no-repeat
    50% 50%;
  background-size: 30px 30px;
}

.detail_product_select_desc .btn {
  overflow: hidden;
  float: left;
  width: 28px;
  height: 28px;
  border: 0;
  font-size: 0;
  line-height: 0;
  text-indent: -9999px;
}

.detail_total_price {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  padding-top: 30px;
  padding-bottom: 14px;
}

.detail_total_price_main {
  font-weight: 700;
  font-size: 13px;
  line-height: 20px;
  vertical-align: 2px;
}

.detail_total_price_main .won {
  padding-left: 8px;
  font-weight: 800;
  font-size: 32px;
  line-height: 32px;
}


.purchase_button {
  display: flex;
  width: 100%;
  align-items: center;
  item-align: center;
  justify-content: end;
  gap: 10px;
  margin-right: 0px;
}

.btn a {
  text-decoration-line: none;
}

.purchase_button .btn {
  /*overflow: hidden;*/
  height: 56px;
  width: 56px;
  margin-right: 0px;
  padding-bottom: 2px;
  border: 1px solid #000000;
  border-radius: 3px;
  background: #fff;
  font-weight: 700;
  font-size: 16px;
  cursor: pointer;
  line-height: 52px;
  letter-spacing: -0.1px;
  text-align: center;
}

.purchase_button .btn_cart {
  width: 200px;
  background-color: dodgerblue;
  margin-right: 0px;
}

.btn_cart a {
  color: #000000;
}

.purchase_button .btn_purchase {
  width: 200px;
  background-color: dodgerblue;
}
.btn_purchase a {
  color: #000000;
}

.btn_heart img {
  margin-top: 7px;
}
.btn_notice img {
  margin-top: 7px;
}

.good-infomations {
  margin: 0 auto;
}
.goods-view-infomation-tab-group li {
  display: flex;
  flex: 1 1 0%;
  border-width: 1px 0px 1px 1px;
  border-color: rgb(238, 238, 238);
  border-style: solid;
  //background-color: rgb(250, 250, 250);
  margin: 0px;
}

.goods-view-infomation-tab-group li a {
  display: flex;
  flex: 1 1 0%;
  -webkit-box-pack: center;
  justify-content: center;
  -webkit-box-align: center;
  align-items: center;
  height: 100%;
}

.good-tip h3 {
  display: inline-block;
  position: relative;
  z-index: 10;
  font-size: 30px;
  padding-left: 48px;
  font-weight: 500;
  color: rgb(102, 102, 102);
  letter-spacing: 1px;
  top: 30px;
}

.good-tip h3 span {
  position: relative;
  z-index: 10;
  display: inline-block;
  padding: 0px 10px;
  font-size: 36px;
  background: rgb(255, 255, 255);
  text-shadow: 2px 0px;
}

.words strong {
  display: block;
  font-size: 25px;
  line-height: 38px;
  margin-bottom: 10px;
}

.good_reviews {
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  padding-top: 70px;
  padding-bottom: 70px;
  /*//position: relative;*/
  width: 1010px;
}

.gr_thumb {
  position: relative;
  width: 124px;
  height: 124px;
  overflow: hidden;
  padding: 0px;
  background-color: rgb(244, 244, 244);
  cursor: pointer;
  border-style: none;
}
.gr_thumb_title span {
  display: block;
}
.gr_thumb_img img {
  width: 124px;
  height: 124px;
}


.good_review_count {
  display: flex;
  justify-content: space-between;
  padding-bottom: 16px;
  border-bottom: 1px solid #333;
}
.gr_count_total span {
  font-size: 14px;
  font-weight: 600;
  line-height: 16px;
}
.gr_order_by {
  display: flex;
  align-items: center;
  gap: 10px;
}

.gr_order_btn {
  font-size: 14px;
  background-color: white;
  text-decoration: none;

  color: inherit;
}

.gr_order_btn.active {
  font-weight: bold;
  font-size: 14px;
}

.good_review_account {
  display: flex;
  padding: 30px 0px 19px 20px;
  border-bottom: 1px solid rgb(244, 244, 244);
  font-size: 14px;
  font-weight: 400;
  line-height: 19px;
}

.gr_a_content {
  display: flex;
  flex-direction: column;
}

.gr_a_content .gr_a_name {
  font-size: 14px;
  font-weight: 400;
  line-height: 19px;
}

.gr_a_name {
  width: 225px;
  display: flex;
  flex-direction: row;
  font-size: 16px;
  font-weight: 400;
  line-height: 22px;
  text-align: center;
  color: rgb(51, 51, 51);
}

.gr_a_name .gr_a_n_grade {
  display: inline-block;
  height: 14px;
  border: 1px solid rgb(77, 190, 215);
  border-radius: 3px;
  padding: 5px 4px 1px 5px;
  margin-right: 4px;
  font-weight: 500;
  font-size: 10px;
  line-height: 10px;
  text-align: center;
  word-break: keep-all;
  background-color: rgb(77, 190, 215);
  color: rgb(255, 255, 255);
}

.gr_a_n_username {
  font-weight: 600;
}

.gr_a_content {
  width: 765px;
}

.gr_a_product_name {
  font-size: 14px;
  font-weight: 400;
  line-height: 19px;
  color: rgb(153, 153, 153);
}

.gr_a_content_detail {
  padding-top: 12px;
  padding-right: 20px;
  word-break: break-word;
  font-weight: 400;
  font-size: 14px;
  line-height: 19px;
  color: rgb(51, 51, 51);
}

.gr_a_content_imgs {
  display: flex;
  flex-wrap: nowrap;
  gap: 3px;
  overflow: auto hidden;
  scrollbar-width: none;
  padding: 15px 0px 2px;
}

.gr_a_content_img {
  width: 93px;
  height: 93px;
  overflow: hidden;
  background-color: rgb(244, 244, 244);
  cursor: pointer;
  border-style: none;
}

.gr_a_content_img img {
  width: 100%;
  height: 100%;
}

.gr_a_content_date {
  display: flex;
  -webkit-box-pack: justify;
  justify-content: space-between;
  -webkit-box-align: center;
  align-items: center;
  width: 100%;
  padding-top: 19px;
  padding-right: 20px;
}

.gr_a_content_date span {
  color: rgb(153, 153, 153);
}
section {
  padding-bottom: 88px;
}

.gr_a_content_thumb {
  display: flex;
  justify-content: center;
  align-items: center;
  min-width: 88px;
  height: 32px;
  padding: 0px 13px 0px 11px;
  border: 1px solid rgb(226, 226, 226);
  border-radius: 20px;
  font-size: 12px;
  line-height: 20px;
  color: rgb(153, 153, 153);
  background-color: transparent;
  cursor: pointer;
}

.gr_a_c_thumb_icon {
  width: 15px;
  height: 15px;
  margin-right: 4px;
}
.mh_btn span {
  width: 100%;
  height: 100%;
  font-size: 27px;
  color: rgb(153, 153, 153);
  font-weight: 100;
}

.modal img {
  max-width: 100%;
  height: auto;
}

.mm_img img {
  width: 100%;
  height: 100%;
}

.mm_review .gr_a_product_name {
  font-size: 12px;
}

.gr_a_c_date span {
  font-size: 12px;
  font-weight: 600;
}
</style>
