<template>
  <div class="calendar">
    <div class="calendar-header">
      <span class="item month-btn" v-on:click="toPrevMonth">
        <i class="btn fa fa-solid fa-caret-left" aria-hidden="true"></i>
      </span>
      <div class="item yearmonth">
        <span class="year"> {{year}}년 </span>
        <span class="month"> {{month}}월 </span>
      </div>
      <span class="item month-btn" v-on:click="toNextMonth">
        <i class="btn fa fa-solid fa-caret-right" aria-hidden="true"></i>
      </span>
    </div>
    <div class="calendar-body">
      <div class="calendar-cell-rows" v-for="row in dayOfWeeks">
        {{row}}
        <TodoCalendarCell class="calendar-cell" v-for="col in 6"></TodoCalendarCell>
      </div>
    </div>
  </div>
</template>

<script>
import TodoCalendarCell from "./TodoCalendarCell"

export default {
  data() {
    return {
      year: 2022,
      month: 7,
      day: 28,
      firstDayOffWeek: 0,
      dayOfWeeks: ['일', '월', '화', '수', '목', '금', '토']
    }
  },
  created () {
    const current = new Date();

    this.year = current.getFullYear();
    this.month = current.getMonth() + 1;
    this.day = current.getDate();
    this.firstDayOffWeek = new Date(this.year, this.month - 1, 1).getDay();
    
  },

  methods: {
    toPrevMonth() {
      if (this.month == 1) {
        this.year--;
        this.month = 12;
      }
      else {
        this.month--;
      }
    },
    toNextMonth() {
      if (this.month == 12) {
        this.month = 1;
        this.year++;
      }
      else {
        this.month++;
      }
    }
  },
  components: {
    TodoCalendarCell: TodoCalendarCell
  }
}

</script>

<style>
  .calendar {
    background-color: #EEE;
    height: 400px;
    width: 80%;
    margin-left: auto;
    margin-right: auto;
  }
  .calendar-header {
    /* height: 50px; */
    background-color: #00DDEE;
    display: flex;
    justify-content: flex-start;
    height: 50px;
    line-height: 50px;
  }
  .calendar-header .item {
    flex: 1 1 1;
  }
  .yearmonth {
    flex: 5 5 auto;
    float: left;
  }
  .month-btn {
    background: linear-gradient(to right, #6478FB, #8763FB);
    width: 3rem;
    border-radius: 0 5px 5px 0;
  }
  .year {
    margin-right: 10px;
    font-size: 30px;
  }
  .month {
    font-size: 30px;
  }
  .btn {
    color: white;
    vertical-align: middle;
  }
  .calendar-body {
    height: 100%;
    display: flex;
    flex-direction: row;
    /* justify-content: space-around; */
    background-color: #aaa;
  }
  .calendar-cell-rows {
    flex: 1 1 1;
    display: flex;
    flex-direction: column;
    /* justify-content: space-around; */
    background-color: #ddd;
    border-color: white;
    width: 14.6%;

  }
  .calendar-cell {
    flex: 1 1 1;
    height: 15%;
    width: 100%;
    border-color: #EEE;
  }
</style>
