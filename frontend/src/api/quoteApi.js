import axios from "axios";

export const fetchQuotes = async () => {
  // const url = "https://api.quotable.io/quotes?tags=nosiejflse";
  const url = "http://localhost:8080/api/quotes";
  try {
    const res = await axios.get(url);
    if (res.status !== 200) {
      throw new Error(`The quotes can't be fetched. Status :${res.status}`);
    } else {
      return res;
    }
  } catch (err) {
    console.error(err);
  }
};
