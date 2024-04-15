<script>
import {reactive} from "vue";
import axios from "axios";
import router from "@/scripts/router";

export default {
  setup() {
    const state = reactive({
      form: {
        name: "",
        description: ""
      }
    });

    const add = () => {
      axios.post("/api/categories", state.form)
          .then((res) => {
            console.log(res.data); // 성공적으로 등록되었을 때 메시지
            router.push("/"); // 카테고리 목록 페이지로 이동
          })
          .catch((error) => {
            console.error("카테고리 등록 실패:", error);
            alert("카테고리 등록에 실패했습니다.");
          });
    };

    return { state, add };
  }
}
</script>

<template>
  <div class="container">
    <main class="form-signin">
      <h1 class="h3 mb-3 fw-normal text-center">카테고리 등록</h1>
      <div class="form-floating">
        <label for="categoryName" class="form-label">카테고리 이름</label>
        <input type="text" class="form-control" id="categoryName" v-model="state.form.name" placeholder="이름을 적어주세요" required style="margin-bottom: 20px">
      </div>
      <div class="form-floating">
        <label for="categoryDesc" class="form-label">카테고리 설명</label>
        <textarea id="categoryDesc" name="categoryDesc" v-model="state.form.description" class="form-control" rows="4" cols="50" style="margin-bottom: 20px"></textarea>
      </div>
      <button @click="add" class="w-100 btn btn-lg btn-success">등록하기</button>
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