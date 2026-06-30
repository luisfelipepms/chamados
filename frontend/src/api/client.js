const BASE_URL = "http://localhost:8080";

async function apiRequest(endpoint, options = {}) {
  const { method = "GET", body, headers = {}, params } = options;

  let url = `${BASE_URL}${endpoint}`;

  // Adiciona query params se existirem
  if (params) {
    const query = new URLSearchParams(params).toString();
    url += `?${query}`;
  }

  const config = {
    method,
    headers: {
      "Content-Type": "application/json",
      ...headers,
    },
  };

  if (body) {
    config.body = JSON.stringify(body);
  }

  try {
    const response = await fetch(url, config);

    // Tenta ler o JSON mesmo em caso de erro (o backend PHP pode retornar mensagem)
    const data = await response.json().catch(() => null);

    if (!response.ok) {
      throw new Error(data?.message || `Erro ${response.status}`);
    }

    return data;
  } catch (error) {
    console.error(`Erro na requisição [${method} ${endpoint}]:`, error.message);
    throw error;
  }
}

export default apiRequest;