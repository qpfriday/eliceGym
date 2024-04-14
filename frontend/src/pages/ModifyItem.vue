<script>
import {reactive, watch} from "vue";
import axios from "axios";
import {useRoute} from "vue-router";

export default {
  name: "modifyItem",
  setup() {
    const route = useRoute();
    const itemId = route.params.itemId;
    const state = reactive({
      loading: true,
      form: {
        name: "",
        price: "",
        discountPer: "",
        parentCategory: "",
        childCategory: "",
        selection: "",
        description: "",
        stock: "",
        deliveryPrice: "",
        imgPath: "",
        imgView: null,
        imgName: "Choose file",
        img: null,
      },
      editable: false
    })

    const load = () => {
      axios.get(`/api/item/${itemId}`).then(({ data }) => {
        console.log(data);
        state.form = data;
        state.img = `data:image/jpeg;base64,` + data.img;
        state.loading = false;
      });
    }

    const edit = () => {
      state.editable = true
    }

    const save = () => {
      console.log(state.form)
      axios.put("/api/item/update", state.form)
      state.editable = false
    }

    load()

    watch(
        () => route.params.itemId,
        (itemId) => {
          console.log(itemId);
        }
    );

    return { state, edit, save }
  }
}
</script>

<template>
  <div class="container" v-if="!state.loading">
    <main class="form-signin">
      <h1 class="h3 mb-3 fw-normal text-center">상품 정보</h1>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">상품명</label>
        <input type="text" class="form-control" id="name" placeholder="상품명을 입력해주세요" aria-label="Disabled input example" disabled
               style="margin-bottom: 20px"
               v-if="!state.editable"
               v-model="state.form.name">
        <input type="text" class="form-control" id="name" placeholder="상품명을 입력해주세요"  required
               style="margin-bottom: 20px"
               v-else
               v-model="state.form.name">
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">가격</label>
        <input type="text" class="form-control" id="price" placeholder="Disabled" aria-label="Disabled input example" disabled
               style="margin-bottom: 20px"
               v-if="!state.editable"
               v-model="state.form.price">
        <input type="text" class="form-control" id="price" placeholder="숫자만 입력해주세요" required
               style="margin-bottom: 20px"
               v-else
               v-model="state.form.price">
      </div>
      <div class="form-floating">
        <label for="floatingPassword" class="form-label">할인율 (%)</label>
        <input type="text" class="form-control" id="discount_per" placeholder="할인율을 입력해주세요" aria-label="Disabled input example" disabled
               style="margin-bottom: 20px"
               v-if="!state.editable"
               v-model="state.form.discountPer">
        <input type="text" class="form-control" id="discount_per" placeholder="할인율을 입력해주세요" required
               style="margin-bottom: 20px"
               v-else
               v-model="state.form.discountPer">
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">상위 카테고리</label>
        <input type="text" class="form-control" id="parent_category" placeholder="상위 카테고리를 입력해주세요" aria-label="Disabled input example" disabled
               style="margin-bottom: 20px"
               v-if="!state.editable"
               v-model="state.form.parentCategory">
        <input type="text" class="form-control" id="parent_category" placeholder="상위 카테고리를 입력해주세요" required
               style="margin-bottom: 20px"
               v-else
               v-model="state.form.parentCategory">
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">하위 카테고리</label>
        <input type="text" class="form-control" id="chile_category" placeholder="하위 카테고리를 입력해주세요" aria-label="Disabled input example" disabled
               style="margin-bottom: 20px"
               v-if="!state.editable"
               v-model="state.form.childCategory">
        <input type="text" class="form-control" id="chile_category" placeholder="하위 카테고리를 입력해주세요" required
               style="margin-bottom: 20px"
               v-else
               v-model="state.form.childCategory">
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">옵션</label>
        <input type="text" class="form-control" id="selection" placeholder="옵션을 입력해주세요" aria-label="Disabled input example" disabled
               style="margin-bottom: 20px"
               v-if="!state.editable"
               v-model="state.form.selection">
        <input type="text" class="form-control" id="selection" placeholder="옵션을 입력해주세요" required
               style="margin-bottom: 20px"
               v-else
               v-model="state.form.selection">
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">상품 설명</label>
        <input type="text" class="form-control" id="floatingInput" placeholder="description" aria-label="Disabled input example" disabled
               style="margin-bottom: 20px"
               v-if="!state.editable"
               v-model="state.form.description">
        <input type="email" class="form-control" id="floatingInput" placeholder="description" required
               style="margin-bottom: 20px"
               v-else
               v-model="state.form.description">
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">수량 (개)</label>
        <input type="text" class="form-control" id="stock" placeholder="숫자만 입력해주세요" aria-label="Disabled input example" disabled
               style="margin-bottom: 20px"
               v-if="!state.editable"
               v-model="state.form.stock">
        <input type="email" class="form-control" id="stock" placeholder="숫자만 입력해주세요" required
               style="margin-bottom: 20px"
               v-else
               v-model="state.form.stock">
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">배송비</label>
        <input type="text" class="form-control" id="delivery_price" placeholder="숫자만 입력해주세요" aria-label="Disabled input example" disabled
               style="margin-bottom: 20px"
               v-if="!state.editable"
               v-model="state.form.deliveryPrice">
        <input type="email" class="form-control" id="delivery_price" placeholder="숫자만 입력해주세요" required
               style="margin-bottom: 20px"
               v-else
               v-model="state.form.deliveryPrice">
      </div>
      <button class="w-100 btn btn-lg btn-success" @click="edit" v-if="!state.editable">수정</button>
      <button class="w-100 btn btn-lg btn-success" @click="save" v-if="state.editable">저장</button>
    </main>
  </div>
  <div v-else class="d-flex justify-content-center align-items-center" style="height: 30vh; margin-top: 200px">
    <div class="spinner-grow text-danger" style="width: 50px; height: 50px" role="status">
      <span class="sr-only">Loading...</span>
    </div>
  </div>
</template>

<style scoped>
.container {
  margin: 50px auto 100px auto;
  width: 700px;
  padding: 100px 50px 100px 50px;
  border: solid lightgray 0.5px;
  border-radius: 10px;
}
</style>
