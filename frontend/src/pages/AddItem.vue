<template>
  <div class="container">
    <div class="form-group row">
      <label class="col-sm-2">상품명</label>
      <div class="col-sm-3">
        <input type="text" name="name" class="form-control" v-model="state.form.name"/>
      </div>
    </div>

    <div class="form-group row">
      <label class="col-sm-2">상품 이미지</label>
      <div class="col-sm-3">
        <!-- <input type="file" name="img_path" class="form-control" @change="handleFileChange" accept="image/gif,image/jpeg,image/png"/> -->
        <input type="text" name="img_path" class="form-control" v-model="state.form.imgPath"/>
      </div>
    </div>

    <div class="form-group row">
      <label class="col-sm-2">가격</label>
      <div class="col-sm-3">
        <input type="text" name="price" class="form-control" v-model="state.form.price"/>
      </div>
    </div>


    <div class="form-group row">
      <label class="col-sm-2">할인율</label>
      <div class="col-sm-3">
        <input name="discount_per" class="form-control" v-model="state.form.discount_per"/>
      </div>
    </div>

    <div class="form-group row">
      <label class="col-sm-2">상위 카테고리</label>
      <div class="col-sm-3">
        <input name="parent_category" class="form-control" v-model="state.form.parent_category"/>
        <!-- <select name="parent_category" class="form-control" v-model="state.form.parent_category">
            <option value="">-- 상위 카테고리 --</option>
            <option value="electronics">전자제품</option>
            <option value="clothing">의류</option>
            <option value="books">도서</option>
        </select> -->
      </div>
    </div>

    <div class="form-group row">
      <label class="col-sm-2">하위 카테고리</label>
      <div class="col-sm-3">
        <input name="chile_category" class="form-control" v-model="state.form.chile_category"/>

        <!-- <select name="chile_category" class="form-control" v-model="state.form.chile_category">
            <option value="">-- 하위 카테고리 --</option>
            <option value="electronics">전자제품</option>
            <option value="clothing">의류</option>
            <option value="books">도서</option>
        </select> -->
      </div>
    </div>


    <div class="form-group row">
      <label class="col-sm-2">selection</label>
      <div class="col-sm-3">
        <input type="text" name="selection" class="form-control" v-model="state.form.selection"/>
      </div>
    </div>

    <div class="form-group row">
      <label class="col-sm-2">상품 설명</label>
      <div class="col-sm-3">
        <textarea type="text" name="description" class="form-control" rows="4" cols="50"
                  v-model="state.form.description"></textarea>
      </div>
    </div>

    <div class="form-group row">
      <label class="col-sm-2">재고</label>
      <div class="col-sm-3">
        <input type="text" name="stock" class="form-control" v-model="state.form.stock"/>
      </div>
    </div>

    <div class="form-group row">
      <label class="col-sm-2">배송비</label>
      <div class="col-sm-3">
        <input type="text" name="delivery_price" class="form-control" v-model="state.form.delivery_price"/>
      </div>
    </div>
    <div class="form-group row">
      <button class="w-100 btn btn-lg btn-success" @click="add">등록하기</button>
    </div>
  </div>
</template>

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

<style scoped>
.container {
  margin-top: 50px;
  width: 1000px;
  padding: 50px 100px 0px 100px;
  border: solid lightgray 0.5px;
  border-radius: 10px
}

div .form-control {
  width: 500px;
  margin: 0px auto 0px auto;
}
</style>