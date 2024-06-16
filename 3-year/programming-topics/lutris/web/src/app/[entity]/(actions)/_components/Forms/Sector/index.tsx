"use client";
import type { Sector } from "~/@types/entities";
import type { FormProps } from "../shared/types";
import Input from "../shared/Input";
import Form from "../shared/Form";
import Submit from "../shared/Submit";
import GoBack from "../shared/GoBack";
import { useForm } from "react-hook-form";
import api from "~/api";
import { useRouter } from "next/navigation";

interface SectorFormProps extends FormProps<Sector> {}

function SectorForm({ data }: SectorFormProps) {
  const router = useRouter();
  const { register, handleSubmit } = useForm<Sector>({
    defaultValues: {
      id: data?.id,
      launchDate: data?.launchDate,
      name: data?.name,
    },
    mode: "onBlur",
    reValidateMode: "onBlur",
  });

  const onSubmit = async (formData: Sector) => {
    try {
      if (data) {
        await api.sectors.update(formData.id, formData);
      } else {
        await api.sectors.create(formData);
      }

      router.push("/setores");
      router.refresh();
    } catch (err) {
      // do nothing
    }
  };

  return (
    <Form onSubmit={handleSubmit(onSubmit)}>
      <Input
        label="Nome"
        placeholder="Engenharia e Produto"
        {...register("name")}
      />
      <div className="self-end flex gap-x-3">
        <GoBack />
        <Submit type={data ? "update" : "create"} />
      </div>
    </Form>
  );
}

export default SectorForm;
