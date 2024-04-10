<script>

export default {
  setup() {

  }
}
</script>

<template>
  <div class="container">
    <main class="form-signin">
      <h1 class="h3 mb-3 fw-normal text-center">카테고리 수정</h1>
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
      <button class="w-100 btn btn-lg btn-success">저장하기</button>
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