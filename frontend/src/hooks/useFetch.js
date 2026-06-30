import { useState, useEffect, useCallback, useRef } from "react";

export function useFetch(fetchFn, dependencies = [], pollingInterval = null) {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const intervalRef = useRef(null);

  const refetch = useCallback(async () => {
    setError(null);
    try {
      const result = await fetchFn();
      setData(result);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, dependencies);

  useEffect(() => {
    refetch();

    if (pollingInterval) {
      intervalRef.current = setInterval(refetch, pollingInterval);
    }

    return () => {
      if (intervalRef.current) clearInterval(intervalRef.current);
    };
  }, [refetch, pollingInterval]);

  return { data, loading, error, refetch };
}