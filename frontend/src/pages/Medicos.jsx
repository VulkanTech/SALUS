import { useEffect, useState } from "react";
import api from "../api/api";

export default function Medicos() {
    const [medicos, setMedicos] = useState([]);
    const [nome, setNome] = useState("");
    const [cpf, setCpf] = useState("");
    const [especialidade, setEspecialidade] = useState("");

    useEffect(() => {
        carregar();
    }, []);

    function carregar() {
        api.get("/medicos").then(res => setMedicos(res.data));
    }

    function salvar(e) {
        e.preventDefault();

        api.post("/medicos", {
            nome,
            cpf,
            especialidade
        }).then(() => {
            setNome("");
            setCpf("");
            setEspecialidade("");
            carregar();
        });
    }

    return (
        <>
            <h2>Médicos</h2>

            <form onSubmit={salvar}>
                <input placeholder="Nome" value={nome} onChange={e => setNome(e.target.value)} />
                <input placeholder="CPF" value={cpf} onChange={e => setCpf(e.target.value)} />
                <input placeholder="Especialidade" value={especialidade} onChange={e => setEspecialidade(e.target.value)} />
                <button type="submit">Cadastrar</button>
            </form>

            <ul>
                {medicos.map(m => (
                    <li key={m.cpf}>
                        {m.nome} – {m.especialidade}
                    </li>
                ))}
            </ul>
        </>
    );
}
