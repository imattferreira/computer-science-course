import type { Unit } from "~/@types/entities";
import type { Optional } from "~/@types/utils";
import request from "~/utils/request";

type UpInsertUnit = Omit<Unit, "id" | "launchDate">;

const endpoints = {
  create: (data: UpInsertUnit) => request.post<Unit>("/units", data),
  findAll: () => request.get<Unit[]>("/units"),
  findById: (id: number) => request.get<Optional<Unit>>(`/units/${id}`),
  update: (id: number, data: UpInsertUnit) =>
    request.put<Unit>(`/units/${id}`, data),
  delete: (id: number) => request.delete<void>(`/units/${id}`),
};

export default endpoints;
