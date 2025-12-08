import { useEffect, useState } from "react";
import api from "../api/api";

export default function Consultas() {
    const [consultas, setConsultas] = useState([]);
    const [medicos, setMedicos] = useState([]);
    const [pacientes, setPacientes] = useState([]);

    const [medicoCpf, setMedicoCpf] = useState("");
    const [pacienteCpf, setPacienteCpf] = useState("");
    const [dataHora, setDataHora] = useState("");

    const [loading, setLoading] = useState(false);
    const [erro, setErro] = useState(null);

    useEffect(() => {
        carregarDados();
    }, []);

    function carregarDados() {
        setLoading(true);

        Promise.all([
            api.get("/consultas"),
            api.get("/medicos"),
            api.get("/pacientes"),
        ])
            .then(([consultasRes, medicosRes, pacientesRes]) => {
                setConsultas(consultasRes.data);
                setMedicos(medicosRes.data);
                setPacientes(pacientesRes.data);
                setErro(null);
            })
            .catch(() => {
                setErro("Erro ao carregar dados");
            })
            .finally(() => setLoading(false));
    }

    function agendarConsulta(e) {
        e.preventDefault();

        if (!medicoCpf || !pacienteCpf || !dataHora) {
            setErro("Preencha todos os campos");
            return;
        }

        setLoading(true);

        api.post("/consultas", {
            medicoCpf,
            pacienteCpf,
            dataHora,
        })
            .then(() => {
                setMedicoCpf("");
                setPacienteCpf("");
                setDataHora("");
                carregarDados();
            })
            .catch(err => {
                setErro(err.response?.data?.message || "Erro ao agendar consulta");
            })
            .finally(() => setLoading(false));
    }

    function cancelarConsulta(id) {
        if (!window.confirm("Deseja cancelar esta consulta?")) return;

        setLoading(true);

        api.delete(`/consultas/${id}`)
            .then(() => carregarDados())
            .catch(() => setErro("Erro ao cancelar consulta"))
            .finally(() => setLoading(false));
    }

    return (
        <div>
            <h2>Consultas</h2>

            {erro && <p style={{ color: "red" }}>{erro}</p>}
            {loading && <p>Carregando...</p>}

            <form onSubmit={agendarConsulta} style={{ marginBottom: 20 }}>
                <select value={medicoCpf} onChange={e => setMedicoCpf(e.target.value)}>
                    <option value="">Selecione o médico</option>
                    {medicos.map(m => (
                        <option key={m.cpf} value={m.cpf}>
                            {m.nome} ({m.especialidade})
                        </option>
                    ))}
                </select>

                <select value={pacienteCpf} onChange={e => setPacienteCpf(e.target.value)}>
                    <option value="">Selecione o paciente</option>
                    {pacientes.map(p => (
                        <option key={p.cpf} value={p.cpf}>
                            {p.nome}
                        </option>
                    ))}
                </select>

                <input
                    type="datetime-local"
                    value={dataHora}
                    onChange={e => setDataHora(e.target.value)}
                />

                <button type="submit">Agendar</button>
            </form>

            <ul>
                {consultas.map(c => (
                    <li key={c.id} style={{ marginBottom: 10 }}>
                        <strong>{new Date(c.dataHora).toLocaleString()}</strong>
                        <br />
                        Médico: {c.medico.nome}
                        <br />
                        Paciente: {c.paciente.nome}
                        <br />
                        <button onClick={() => cancelarConsulta(c.id)}>
                            Cancelar
                        </button>
                    </li>
                ))}
            </ul>
        </div>
    );
}
