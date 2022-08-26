<template>
  <header>
    <h1>TodoList</h1>
    <div class="todo-header">
      <span class="month-btn" v-on:click="toPrevMonth">
        <i class="addBtn fa fa-solid fa-angle-left fa-2x" aria-hidden="true"></i>
      </span>

      <span class="year-month">{{ propsYear }}년 {{ propsMonth }}월</span>

      <span class="month-btn" v-on:click="toNextMonth">
        <i class="addBtn fa fa-angle-right fa-2x" aria-hidden="true"></i>
      </span>
    </div>
  </header>
</template>

<script>
export default {
  props: ['propsYear', 'propsMonth'],

  methods: {
    toNextMonth() {
      this.$emit("toNextMonth")
    },
    toPrevMonth() {
      this.$emit("toPrevMonth")
    },
  }
}
</script>

<style scoped>
  h1 {
    color: #2F3B52;
    font-weight: 900;
    margin: 2.5rem 0 1.5rem;
  }

  .todo-header {
    display: flex;
    margin-left: auto;
    margin-right: auto;
    justify-content: flex-start;
  }

  .year-month {
    flex: 1;
    font-size: 2rem;
    padding-left: 4rem;
    padding-right: 4rem;
  }

  .month-btn {
    flex: 1;
    border-radius: 0 5px 5px 0;
  }

  .addBtn {
    color: #000;
  }

</style>
