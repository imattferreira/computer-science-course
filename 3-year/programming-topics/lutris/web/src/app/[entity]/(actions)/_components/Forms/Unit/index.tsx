"use client";
import type { Unit } from "~/@types/entities";
import { FormProps } from "../shared/types";
import Input from "../shared/Input";
import Form from "../shared/Form";
import Submit from "../shared/Submit";
import GoBack from "../shared/GoBack";
import { useForm } from "react-hook-form";
import api from "~/api";
import { useRouter } from "next/navigation";

interface UnitFormProps extends FormProps<Unit> {}

function UnitForm({ data }: UnitFormProps) {
  const router = useRouter();
  const { register, handleSubmit } = useForm<Unit>({
    defaultValues: {
      id: data?.id,
      launchDate: data?.launchDate,
      name: data?.name,
    },
    mode: "onBlur",
    reValidateMode: "onBlur",
  });

  const onSubmit = async (formData: Unit) => {
    try {
      if (data) {
        api.units.update(formData.id, formData);
      } else {
        api.units.create(formData);
      }

      router.push("/unidades");
      router.refresh();
    } catch {
      // do nothing
    }
  };

  return (
    <Form onSubmit={handleSubmit(onSubmit)}>
      <Input
        label="Nome"
        placeholder="Matriz Bauru, SP"
        {...register("name")}
      />
      <div className="self-end flex gap-x-3">
        <GoBack />
        <Submit type={data ? "update" : "create"} />
      </div>
    </Form>
  );
}

export default UnitForm;
