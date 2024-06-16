type HttpMethods = "GET" | "POST" | "PUT" | "DELETE";

type Body = Record<string, unknown>;

interface RequesterInput {
  endpoint: string;
  method: HttpMethods;
  body?: Body | FormData;
}

type Requester =
  | (<T>(endpoint: string) => Promise<T>)
  | (<T>(endpoint: string, data: Body) => Promise<T>);

async function requester<T>({
  endpoint,
  method,
  body,
}: RequesterInput): Promise<T> {
  const url = "http://192.168.7.3:8080" + endpoint;
  // const url = process.env.NEXT_PUBLIC_API_URL + endpoint;
  const headers = new Headers();
  const fetchArgs: RequestInit = {
    method,
    headers,
    next: { revalidate: 0 },
  };

  try {
    if (body) {
      if (body instanceof FormData) {
        // headers.append("Content-Type", "multipart/form-data");
        fetchArgs.body = body;
      } else {
        headers.append("Content-Type", "application/json");
        fetchArgs.body = JSON.stringify(body);
      }

      fetchArgs.headers = headers;
    }

    const res = await fetch(url, fetchArgs);

    if (res.status === 404 || res.status === 204) {
      return null as T;
    }

    if (!res.ok) {
      throw new Error(res.status.toString());
    }

    const json = await res.json();

    return json as T;
  } catch (error) {
    console.log(error);
    throw new Error("request error");
  }
}

const request = {
  get: <T>(endpoint: string) => requester<T>({ endpoint, method: "GET" }),
  post: <T>(endpoint: string, data: Body | FormData) =>
    requester<T>({ endpoint, method: "POST", body: data }),
  put: <T>(endpoint: string, data: Body | FormData) =>
    requester<T>({ endpoint, method: "PUT", body: data }),
  delete: async <T>(endpoint: string) =>
    requester<T>({ endpoint, method: "DELETE" }),
} satisfies Record<Lowercase<HttpMethods>, Requester>;

export default request;
