import type { Sector } from "~/@types/entities";
import type { Optional } from "~/@types/utils";
import request from "~/utils/request";

type UpInsertSector = Omit<Sector, "id" | "launchDate">;

const endpoints = {
  create: (data: UpInsertSector) => request.post<Sector>("/sectors", data),
  findAll: () => request.get<Sector[]>("/sectors"),
  findById: (id: number) => request.get<Optional<Sector>>(`/sectors/${id}`),
  update: (id: number, data: UpInsertSector) =>
    request.put<Sector>(`/sectors/${id}`, data),
  delete: (id: number) => request.delete<void>(`/sectors/${id}`),
};

export default endpoints;
