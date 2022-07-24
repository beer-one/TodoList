
let DateFormatter = {
  format(date) {
    function toStr(integer) {
      if(integer < 10) {
        return `0${integer}`;
      }
      else {
        return `${integer}`;
      };
    }

    const day = toStr(date.getDate());
    const month = toStr(date.getMonth() + 1);
    const year = date.getFullYear();
    const hour = toStr(date.getHours());
    const minute = toStr(date.getMinutes());

    return `${year}-${month}-${day} ${hour}:${minute}:00`;
  }
}

export default DateFormatter;
