import QuoteCard from "./QuoteCard";
import { fetchQuotes } from "../api/quoteApi";
import { useState, useEffect } from "react";
import LoadingSpinner from "./LoadingSpinner";

const QuoteList = () => {
  const [quotes, setQuotes] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const getQuotes = async () => {
      const quoteData = await fetchQuotes();
      setQuotes(quoteData?.data);
      setLoading(false);
    };
    getQuotes();
  }, []);

  return (
    <>
      {loading ? (
        <LoadingSpinner />
      ) : (
        <>
          {quotes.length === 0 ? (
            <div className="text-center py-16">
              <p className="text-2xl font-bold mb-2">
                Oh! There's no quote yet.
              </p>
              <p className="text-2xl">Fancy to add some?</p>
            </div>
          ) : (
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 p-4 gap-4 md:p-8 lg:p-16 md:gap-6 lg:gap-8">
              {quotes.map((quote) => (
                <QuoteCard quote={quote} key={quote.id} />
              ))}
            </div>
          )}
        </>
      )}
    </>
  );
};

export default QuoteList;
