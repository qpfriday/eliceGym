<script>
export default {
  methods: {
    scrollToTop() {
      window.scrollTo({top: 0, behavior: 'smooth'});
    },
    scrollToBottom() {
      window.scrollTo({top: document.body.scrollHeight, behavior: 'smooth'});
    }
  }
}
</script>

<template>
  <div class="scroll-controls">
    <button @click="scrollToTop()">
      <i class="bi bi-chevron-up"></i>
    </button>
    <button @click="scrollToBottom()">
      <i class="bi bi-chevron-down"></i>
    </button>
  </div>
</template>

<style scoped>
.scroll-controls {
  position: fixed;
  bottom: 20px;
  right: 20px;
  display: flex;
  flex-direction: column;
}

.scroll-controls button {
  margin-top: 10px;
  padding: 0; /* padding을 제거하거나 줄여서 버튼 크기를 조절 */
  width: 50px; /* 버튼의 너비와 높이를 같게 설정하여 원형 만들기 */
  height: 50px;
  background-color: #fff; /* 배경색을 흰색으로 설정 */
  border: none; /* 테두리 선 제거 */
  border-radius: 50%; /* 완전한 원형 버튼 스타일 */
  box-shadow: 0 2px 4px rgba(0,0,0,0.2); /* 그림자 효과 추가 */
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  outline: none; /* 버튼 클릭 시 발생하는 아웃라인 제거 */
}

.scroll-controls button i {
  font-size: 24px; /* 아이콘 크기 조정 */
  color: #000; /* 아이콘 색상을 검은색으로 설정 */
}

.scroll-controls button:hover {
  background-color: #e9ecef; /* 버튼 호버 시 배경색 변경 */
  box-shadow: 0 4px 8px rgba(0,0,0,0.3); /* 호버 시 그림자 효과 강화 */
}
</style>