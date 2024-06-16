import type { Dependent } from "~/@types/entities";
import request from "~/utils/request";
import type { Optional } from "~/@types/utils";

type UpInsertDependent = Omit<Dependent, "id">;

const endpoints = {
  create: (data: UpInsertDependent) =>
    request.post<Dependent>("/dependents", data),
  findAll: () => request.get<Dependent[]>("/dependents"),
  findById: (id: number) =>
    request.get<Optional<Dependent>>(`/dependents/${id}`),
  update: (id: number, data: UpInsertDependent) =>
    request.put<Dependent>(`/dependents/${id}`, data),
  delete: (id: number) => request.delete<void>(`/dependents/${id}`),
};

export default endpoints;
