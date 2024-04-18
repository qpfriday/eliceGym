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
    <button @click="scrollToTop">
      <i class="bi bi-arrow-up-circle"></i>
    </button>
    <button @click="scrollToBottom">
      <i class="bi bi-arrow-down-circle"></i>
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
button {
  margin-top: 10px;
  padding: 5px;
  background-color: transparent;
  border: none;
  cursor: pointer;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
}
button i {
  font-size: 35px;
  color: #6c757d;
}
button:hover i {
  color: #000;
}
</style>