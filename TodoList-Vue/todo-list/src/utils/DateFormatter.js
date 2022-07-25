
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
  },

  toDate(dateString) {
    let d = dateString.split(' ')[0].split('-');
    let t = dateString.split(' ')[1].split(':');

    return new Date(d[0], d[1] - 1, d[2], t[0], t[1], t[2]);
  }
}

export default DateFormatter;
