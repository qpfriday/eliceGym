<script>

import axios from "axios";

export default {
  setup() {
    const registerCategory = () => {
      const categoryName = document.getElementById("categoryName").value;
      const categoryDesc = document.getElementById("categoryDesc").value;

      // 카테고리 이름과 설명이 모두 입력되었는지 확인
      if (!categoryName || !categoryDesc) {
        alert("카테고리 이름과 설명을 모두 입력해주세요.");
        return;
      }

      // 카테고리 정보를 서버로 전송
      axios.post("/api/categories", {
        name: categoryName,
        desc: categoryDesc
      })
          .then(() => {
            alert("카테고리가 성공적으로 등록되었습니다.");
            // 등록 후 필요한 작업 수행 (예: 화면 갱신)
          })
          .catch(error => {
            console.error("카테고리 등록 실패:", error);
            alert("카테고리 등록에 실패했습니다.");
          });
    };

    return { registerCategory };
  }
}
</script>

<template>
  <div class="container">
    <main class="form-signin">
      <h1 class="h3 mb-3 fw-normal text-center">카테고리 등록</h1>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">카테고리 이름</label>
        <input type="text" class="form-control" id="stock" placeholder="이름을 적어주세요"  required
               style="margin-bottom: 20px">
      </div>
      <div class="form-floating">
        <label for="floatingInput" class="form-label">카테고리 설명</label>
        <textarea name="description" class="form-control" rows="4" cols="50"
                  style="margin-bottom: 20px"></textarea>
      </div>
      <button @click="registerCategory" class="w-100 btn btn-lg btn-success">등록하기</button>
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