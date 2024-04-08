
<script>
import {reactive} from "vue";
import axios from "axios";
import store from "@/scripts/store";
import router from "@/scripts/router";

export default {
  setup() {
    const state = reactive({
      form: {
        name: "",
        imgPath: "",
        price: "",
        discount_per: "",
        parent_category: "",
        chile_category: "",
        selection: "",
        description: "",
        stock: "",
        delivery_price: ""
      }
    })

    const add = () => {
      axios.post("/api/add/item", state.form).then((res) => {
        console.log(res);
        store.commit("setAccount", res.data);
        sessionStorage.setItem("id", res.data);
        router.push({path: "/"});
        window.alert("상품등록이 완료되었습니다.");
      }).catch(() => {
        window.alert("상품등록에 실패하였습니다.");
      })
    }
    return {state, add}
  }
}
</script>

<template>
  <div class="container">
    <main class="form-signin">
      <h1 class="h3 mb-3 fw-normal text-center">상품 추가</h1>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">상품명</label>
        <input type="text" class="form-control" id="name" placeholder="상품명을 입력해주세요"  required
               style="margin-bottom: 20px"
               v-model="state.form.name">
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">상품 이미지</label>
        <input type="text" class="form-control" id="imgPath" placeholder="이미지 주소"  required
               style="margin-bottom: 20px"
               v-model="state.form.imgPath">
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">가격</label>
        <input type="text" class="form-control" id="price" placeholder="숫자만 입력해주세요"  required
               style="margin-bottom: 20px"
               v-model="state.form.price">
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">할인율 (%)</label>
        <input type="text" class="form-control" id="discount_per" placeholder="할인율을 입력해주세요"  required
               style="margin-bottom: 20px"
               v-model="state.form.discount_per">
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">상위 카테고리</label>
        <input type="text" class="form-control" id="parent_category" placeholder="상위 카테고리를 입력해주세요"  required
               style="margin-bottom: 20px"
               v-model="state.form.parent_category">
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">하위 카테고리</label>
        <input type="text" class="form-control" id="chile_category" placeholder="하위 카테고리를 입력해주세요"  required
               style="margin-bottom: 20px"
               v-model="state.form.chile_category">
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">옵션</label>
        <input type="text" class="form-control" id="selection" placeholder="옵션을 입력해주세요"  required
               style="margin-bottom: 20px"
               v-model="state.form.selection">
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">상품 설명</label>
        <textarea name="description" class="form-control" rows="4" cols="50"
                  style="margin-bottom: 20px"
                  v-model="state.form.description"></textarea>
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">재고</label>
        <input type="text" class="form-control" id="stock" placeholder="숫자만 입력해주세요"  required
               style="margin-bottom: 20px"
               v-model="state.form.stock">
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">배송비</label>
        <input type="text" class="form-control" id="delivery_price" placeholder="숫자만 입력해주세요"  required
               style="margin-bottom: 20px"
               v-model="state.form.delivery_price">
      </div>
      <button class="w-100 btn btn-lg btn-success" @click="add">등록하기</button>
    </main>
  </div>
</template>


<style scoped>
.container {
  margin: 50px auto 100px auto;
  width: 700px;
  padding: 100px 50px 100px 50px;
  border: solid lightgray 0.5px;
  border-radius: 10px
}
</style>