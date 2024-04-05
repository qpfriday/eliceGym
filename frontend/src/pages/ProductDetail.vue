<script>
import axios from "axios";
import { watch } from "vue";
import { useRoute } from "vue-router";
import { reactive } from "vue";

export default {
  name: "ProductDetail",
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

    watch(
      () => route.params.itemId,
      (itemId) => {
        console.log(itemId);
      }
    );

    return { state, increaseStock, decreaseQuantity };
  },
};
</script>
<template>
  <div class="container">
    <header class="header-container w3-xlarge">
      <p class="w3-left">EliceGym</p>
      <p class="w3-right">
        <i class="fa fa-shopping-cart w3-margin-right"></i>
        <i class="fa fa-search"></i>
      </p>
    </header>

    <div class="container" id="container">
      <div class="detail_main">
        <div class="section_view">
          <div class="section_left">
            <div class="thumb">
              <img src="/img/protein.png" alt="상품 대표 이미지" class="bg" />
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
                <span class="per">[{{ state.item.discountPer }}%]</span>
                <span id="gd_price" data-price="259000">
                  {{ state.item.price }}</span
                ><span class="money_kor">원</span>
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
                    state.item.price * state.quantity
                  }}</span>
                  <span>원</span>
                </span>
              </div>
              <!--                        <div class="detail_total_price_bottom">-->
              <!--                            <span class="ico">적립</span>-->
              <!--                            <span class="point">-->
              <!--                  구매 시-->
              <!--                  <strong class="emph">0원 적립</strong>-->
              <!--                </span>-->
              <!--                        </div>-->
            </div>

            <div class="purchase_button">
              <!--                        <div class="first_btn">-->
              <!--                            <button type="button" class="btn btn_heart">-->
              <!--                  <span>-->
              <!--                    <img-->
              <!--                            src=""-->
              <!--                            alt="상품 찜하기"-->
              <!--                            class="logo_img"-->
              <!--                    />-->
              <!--                  </span>-->
              <!--                            </button>-->
              <!--                        </div>-->
              <!--                        <div class="sec_btn">-->
              <!--                            <button type="button" class="btn btn_notice">-->
              <!--                  <span>-->
              <!--                    <img-->
              <!--                            src="/img/product_notice.svg"-->
              <!--                            alt="상품 알림 설정"-->
              <!--                            class="logo_img"-->
              <!--                    />-->
              <!--                  </span>-->
              <!--                            </button>-->
              <!--                        </div>-->
              <div class="sec_btn">
                <button type="button" class="btn btn_purchase">
                  <a href="OrderPage.html">구매하기</a>
                </button>
              </div>
              <div class="third_btn">
                <button type="button" class="btn btn_cart">
                  <a href="OrderPage.html">장바구니 담기</a>
                </button>
              </div>
            </div>
          </div>
        </div>

        <!--            -->

        <div class="good-infomations">
          <!-- <nav class="infomations-tabs">
            <ul class="goods-view-infomation-tab-group">
              <li class="goods-view-infomation-tab">
                <a
                  href="#goods-description"
                  class="goods-view-infomation-tab-anchor active"
                  >상품설명</a
                >
              </li>
              <li class="goods-view-infomation-tab">
                <a
                  href="#goods-infomation"
                  class="goods-view-infomation-tab-anchor"
                  >상세정보</a
                >
              </li>
              <li class="goods-view-infomation-tab">
                <a href="#goods-review" class="goods-view-infomation-tab-anchor"
                  >후기</a
                >
              </li>
              <li class="goods-view-infomation-tab">
                <a
                  href="#goods-inquiry"
                  class="goods-view-infomation-tab-anchor"
                >
                  문의
                </a>
              </li>
            </ul>
            <span class="et-infomations-tab-slider"></span>
          </nav> -->

          <!-- <main class="good-infomation-main">
            <section class="et-slide tab-description" id="goods-description">
              <div class="good-description-main-picture">
                <img src="/img/protein.png" alt="상품 대표 이미지" class="bg" />
              </div>
              <div class="good-description-main-context">
                <h3 class="gd-title">[제품 설명]</h3>
                <p class="gd-desc">1.제품 설명 1.제품 설명 1.제품 설명</p>
                <div class="good-tip">
                  <h3>
                    <span>Tip</span>
                  </h3>
                  <div class="tip-box">
                    <div class="tb-context">
                      <p class="words">
                        <strong>제품 꿀팁</strong>
                        <b>・</b>
                        0℃ 이하에서 냉장 보관하세요.
                        <br />
                        <b>・</b>
                        ~~~~하세요
                        <br />
                      </p>
                    </div>
                  </div>
                </div>
              </div>
            </section>
            <section class="et-slide" id="goods-infomation">
              <div class="good-description-main-picture">
                <img
                  src="/img/product_test1.jpg"
                  alt="상품 대표 이미지"
                  class="bg"
                />
              </div>
              <div class="good-infomation-main-context">
                <h3 class="gd-title">About Brand</h3>
                <p class="words">
                  <strong class="sub_tit">
                    <span class="option_tit">상세 정보</span>
                  </strong>
                  <br />
                  <b>・중량</b>
                  : 중량 입력
                  <br />
                  <b>・특징</b>
                  : 특징 입력
                  <br />
                  <b>・보관법</b>
                  : 보관법 입력
                  <br />
                  <b>・조리법</b>
                  : 조리법 입력
                </p>
              </div>
            </section>

            <section class="et-slide" id="goods-inquery"></section>
          </main> -->

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

            <!-- <div class="good_review_account">
              <div class="gr_a_name">
                <span class="gr_a_n_grade">회원</span>
                <span class="gr_a_n_username">엘**</span>
              </div>

              <div class="gr_a_content">
                <div class="gr_a_product_name">[리뷰 상품 이름] 상품 이름</div>
                <div class="gr_a_content_detail">
                  <p>
                    리뷰 내용 입니다. 리뷰 내용 입니다. 리뷰 내용 입니다. 리뷰
                    내용 입니다. 리뷰 내용 입니다.
                  </p>
                </div>
                <div class="gr_a_content_imgs">
                  <button class="gr_a_content_img gr_thumb">
                    <img src="/img/product_test1.jpg" />
                  </button>
                  <button class="gr_a_content_img gr_thumb">
                    <img src="/img/product_test1.jpg" />
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
            </div> -->
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

/* ====================================main==================================== */

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

.section_left .thumb .bg {
  width: 430px;
  height: 552px;
}

.section_right {
  width: 560px;
}

.section_right > div {
  width: 100%;
}
.detail_header {
  width: 100%;
  font-weight: 600;
  line-height: 1.36;
  letter-spacing: -0.5px;
  color: rgb(153, 153, 153);
  margin-bottom: 6px;
  font-size: 14px;
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

#btnShare {
  right: 0;
  top: 0;
  width: 40px;
  height: 40px;
  border: 0;
  background: url(https://res.kurly.com/mobile/service/goodsview/1910/ico_view_sns.png)
    no-repeat 50% 50%;
  font-size: 0;
  text-indent: -9999px;
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

.money_kor {
  font-size: 20px;
}

.detail_origin {
  color: rgb(51, 51, 51);
  font-size: 24px;
  letter-spacing: -0.5px;
  margin-top: 8px;
}

.detail_delivery {
  width: 100%;
  display: flex;
  height: 57px;
  padding-top: 17px;
  padding-top: 17px;
  margin-top: 1em;
  flex-direction: row;
  border-top: 1px solid #e5e5e5;
  border-bottom: 1px solid #e5e5e5;
}

.detail_delivery .detail_delivery_title {
  align-items: flex-start;
  width: 128px;
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
.detail_delivery_desc .second {
  display: block;
  font-size: 12px;
  color: rgb(102, 102, 102);
  padding-top: 4px;
  line-height: 16px;
  white-space: pre-line;
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

.detail_pack_type {
  width: 100%;
  display: flex;
  /* height: 57px; */
  padding-top: 17px;
  padding-bottom: 17px;
  flex-direction: row;
  border-bottom: 1px solid #e5e5e5;
}

.detail_pack_type .detail_pack_type_title {
  align-items: flex-start;
  width: 128px;
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
.detail_pack_type_desc .second {
  display: block;
  font-size: 12px;
  color: rgb(102, 102, 102);
  padding-top: 4px;
  line-height: 16px;
  white-space: pre-line;
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

.detail_total_price_bottom {
  padding-top: 14px;
}
.detail_total_price_bottom .ico {
  display: inline-block;
  width: 38px;
  height: 20px;
  margin-right: 2px;
  border: 1px solid #e8a828;
  border-radius: 10px;
  background-color: #ffbf00;
  font-weight: 700;
  font-size: 11px;
  color: #fff;
  line-height: 18px;
  text-align: center;
  vertical-align: 1px;
}

.detail_total_price_bottom .point {
  font-size: 14px;
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
.purchase_button .btn_cart:hover {
  box-shadow: 2px 2px 2px 2px gray;
}
.purchase_button .btn_purchase:hover {
  box-shadow: 2px 2px 2px 2px gray;
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

.sec_btn .btn_notice {
}

.btn_heart img {
  margin-top: 7px;
}
.btn_notice img {
  margin-top: 7px;
}

/* ====================================infomations------------- */

.good-infomations {
  margin: 0 auto;
}
.infomations-tabs {
  z-index: 10;
  position: sticky;
  margin: 0px auto;
  top: 0px;
  width: 1010px;
  box-shadow: rgb(221, 221, 221) 0px -0.5px 0px 0px inset;
  background-color: rgb(255, 255, 255);
  margin-top: 50px;
}

.goods-view-infomation-tab-group {
  display: flex;
  width: 100%;
  height: 60px;
  padding: 0px;
}

.goods-view-infomation-tab {
  display: flex;
  justify-content: center;
  align-items: center;
  item-align: center;
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

.goods-view-infomation-tab-anchor {
  background-color: rgb(250, 250, 250);
  text-decoration: none;
  font-weight: bold;
  color: inherit;
}
.goods-view-infomation-tab-anchor.active {
  background-color: white;
}

.infomations-tabs--top {
  position: fixed;
  top: 100;
}

.good-infomation-main .et-slide {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.good-description-main-picture {
}

.good-description-main-picture img {
  width: 1010px;
  height: 670px;
}

.good-description-main-context {
  width: 1010px;
}

.good-description-main-context .gd-title {
  display: block;
  margin: 75px 0px 0px;
  padding-bottom: 35px;
  font-size: 38px;
  line-height: 46px;
  text-align: center;
  border-bottom: 1px solid rgb(193, 193, 193);
  color: rgb(102, 102, 102);
}

.good-infomation-main-context .gd-title {
  display: block;
  margin: 75px 0px 0px;
  padding-bottom: 35px;
  font-size: 38px;
  line-height: 46px;
  text-align: center;
  border-bottom: 1px solid rgb(193, 193, 193);
  color: rgb(102, 102, 102);
}

.good-description-main-context .gd-desc {
  font-size: 18px;
  line-height: 32px;
  color: rgb(55, 55, 55);
  margin-top: 28px;
}

.good-tip {
  margin: 0;
  margin-bottom: 170px;
  display: block;
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

.tip-box {
  margin-top: -15px;
  border: 1px solid rgb(185, 185, 185);
}

.tip-box .tb-context {
  margin: 60px 62px;
}

.words {
  margin: 0px;
  font-size: 18px;
  line-height: 32px;
}
.words strong {
  display: block;
  font-size: 25px;
  line-height: 38px;
  margin-bottom: 10px;
}

/* ====================================상품 후기==================================== */
.good_reviews {
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  padding-top: 70px;
  padding-bottom: 70px;
  /*//position: relative;*/
  width: 1010px;
}

.gr_thumbs {
  display: flex;
  /*//position: relative;*/
  width: 100%;
  gap: 3px;
  align-items: center;
  flex-wrap: nowrap;
  justify-content: start;
  padding: 20px 0px 30px;
  overflow: hidden;
  scrollbar-width: none;
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
.gr_thumb:first-child {
  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
}
.gr_thumb:last-child {
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
}

.gr_thumb_title span {
  display: block;
}
.gr_thumb .gr_thumb_title {
  display: block;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.gr_thumb_title .gr_thumb_title_font {
  color: #fff;
  font-size: 14px;
  font-weight: bold;
}
.gr_thumb_img img {
  width: 124px;
  height: 124px;
}

.gr_thumb .gr_thumb_img {
  display: block;
  width: 100%;
  height: 100%;
  vertical-align: middle;
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

.good_review_grade {
  padding-bottom: 20px;
  border-bottom: 1px solid #333;
}
.gr_grade_btn {
  font-size: 14px;
  color: #333;
  border: 1px solid rgb(238, 238, 238);
  background-color: rgb(255, 255, 255);
  align-items: center;
  padding: 9px 10px 9px 10px;
  border-radius: 18px;
}

.good_review_announce {
  display: flex;
}

.gr_announce {
  width: 100%;
  display: flex;
  padding: 21px 20px 20px;
  border-bottom: 1px solid rgb(238, 238, 238);
}
.gr_a_type {
  display: inline-block;
  height: 22px;
  width: 42px;
  border-radius: 4px;
  background-color: rgb(244, 244, 244);
  font-size: 12px;
  font-weight: 500;
  line-height: 22px;
  text-align: center;
  color: rgb(102, 102, 102);
  vertical-align: 2px;
}

.gr_a_title {
  margin-left: 9px;
  font-size: 16px;
  font-weight: 400;
  line-height: 22px;
  text-align: center;
  color: rgb(51, 51, 51);
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

.gr_a_content_img:first-child {
  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
}
.gr_a_content_img:last-child {
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
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

/* ====================================모달==================================== */

.background {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5); /* Adjust the opacity as needed */
  backdrop-filter: blur(5px); /* Adjust the blur amount as needed */
  z-index: 999;
  display: none;
  overflow: hidden;
}

.modal-container {
  display: none;
  justify-content: center;
  align-items: center;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 1000;
}

.modal {
  background-color: white;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
  position: absolute;
  width: 800px;

  margin: 0px auto;
  background-color: rgb(255, 255, 255);
  border-radius: 12px;
}

.modal_btn {
  position: relative;
  top: 300px;
  left: -100px;
  width: 40px;
  height: 50px;
  border: 0;
  cursor: pointer;
  overflow: hidden;
}
.modal_btn_r {
  position: relative;
  top: 0;
  left: 480px;
  width: 40px;
  height: 50px;
  border: 0;
  cursor: pointer;
  overflow: hidden;
}

.modal_next_btn {
  width: 40px;
  height: 50px;
  border: 0;
  cursor: pointer;
  text-transform: none;
  background-color: transparent;
}
.modal_next_btn span > svg {
  width: 100%;
  height: 100%;
  font-size: 50px;
  color: rgb(153, 153, 153);
  font-weight: 100;
}

.modal .modal_header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-bottom: 8px;
  margin: 29px 30px 20px;
  border-bottom: 1px solid rgb(244, 244, 244);
}

.mh_title {
  font-weight: 600;
  font-size: 24px;
  line-height: 30px;
  letter-spacing: -0.5px;
  color: rgb(51, 51, 51);
}
.mh_btn {
  width: 40px;
  height: 32px;
  border: 0;
  cursor: pointer;
  background-color: transparent;
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

.modal_main {
  display: flex;
  padding: 0px 30px 30px 30px;
  justify-content: space-between;
}

.mm_img {
  width: 375px;
  height: 488px;
}

.mm_img img {
  width: 100%;
  height: 100%;
}

.mm_review {
  display: flex;
  flex-direction: column;
  width: 343px;
}
.mm_review .gr_a_product_name {
  font-size: 12px;
}

.gr_a_c_date span {
  font-size: 12px;
  font-weight: 600;
}
</style>
